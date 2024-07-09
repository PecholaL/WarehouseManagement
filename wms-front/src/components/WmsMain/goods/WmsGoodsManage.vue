<template>
    <div>
        <div style="margin-bottom: 1%;" align="left">
            <el-input v-model="name" 
                size="small" placeholder="请输入货物名" 
                suffix-icon="el-icon-search" style="width: 15%;" 
                @keyup.enter.native="loadPost">
            </el-input>
            <el-button size="mini" type="primary" style="margin-left: 5px;" @click="loadPost">查询</el-button>
            <el-button size="mini" type="success" style="margin-left: 5px" @click="resetParam">重置</el-button>
            <el-button size="mini" type="primary" style="margin-left: 5px;" @click="add">新增</el-button>
        </div>
        <el-table
            :data="tableData"
            :header-cell-style="{background:'#ffcc99', color:'#555'}"
        >
            <el-table-column prop="id" label="ID" min-width="8%">
            </el-table-column>
            <el-table-column prop="name" label="货物" min-width="20%">
            </el-table-column>
            <el-table-column prop="goodstype" label="类型" min-width="10%" :formatter="formatGoodsType">
            </el-table-column>
            <el-table-column prop="storage" label="仓库" min-width="10%" :formatter="formatStorage">
            </el-table-column>
            <el-table-column prop="count" label="数量" min-width="10%">
            </el-table-column>
            <el-table-column prop="note" label="备注" min-width="46%">
            </el-table-column>
            <el-table-column prop="operate" label="操作" min-width="15%">
                <template slot-scope="scope">
                    <el-button size="small" type="success" @click="modify(scope.row)">编辑</el-button>
                    <el-popconfirm title="确定删除吗？" @confirm="del(scope.row.id)">
                        <el-button size="small" slot="reference" type="danger" style="margin-left: 5px;">删除</el-button>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>
        
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="pageNum"
            :page-sizes="[1, 2, 5, 10]"
            :page-size="pageSize"
            layout="total, sizes, prev, pager, next, jumper"
            :total="total">
        </el-pagination>

        <el-dialog
            title="提示"
            :visible.sync="dialogVisible"
            width="30%"
            center>
            <el-form ref="form" :rules="rules" :model="form" label-width="80px">
                <el-form-item label="货物" prop="name">
                    <el-col :span="18">
                        <el-input v-model="form.name"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="类型" prop="goodstype">
                    <el-col :span="18">
                        <el-input type="textarea" v-model="form.goodstype"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="仓库" prop="storage">
                    <el-col :span="18">
                        <el-input type="textarea" v-model="form.storage"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="数量" prop="count">
                    <el-col :span="18">
                        <el-input type="textarea" v-model="form.count"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="备注" prop="note">
                    <el-col :span="18">
                        <el-input type="textarea" v-model="form.note"></el-input>
                    </el-col>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button size="mini" @click="dialogVisible = false">取 消</el-button>
                <el-button size="mini" type="primary" @click="save">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
export default {
    name: "WmsGoodsManage",
    data() {
        let checkCount = (rule, value, callback) => {
            if(value>9999) {
                callback(new Error('数量过大'));
            } else {
                callback();
            }
        };

        return {
            storageData: [],
            tableData: [],
            pageSize: 5,
            pageNum: 1,
            total: 0,
            name: '',
            dialogVisible: false,
            saveOrModify: '',
            form: {
                name: '',
                goodstype: '',
                storage: '',
                count: '',
                note: ''
            },
            rules: {
                name: [
                    {required: true, message: '请输入货物名', trigger: 'blur'}
                ],
                goodstype: [
                    {required: false, message: '请输入货物类型', trigger: 'blur'}
                ],
                storage: [
                    {required: true, message: '请输入仓库名', trigger: 'blur'}
                ],
                count: [
                    {required: true, message: '请输入数量', trigger: 'blur'},
                    {pattern: /^([1-9][0-9]*){1,4}$/, message: '数量必须为整数', trigger: 'blur'},
                    {validator: checkCount, trigger: 'blur'}
                ],
                note: [
                    {required: false, message: '备注', trigger: 'blur'}
                ]
            }
        }
    },
    methods: {
        loadGet() {
            this.$axios.get(this.$httpUrl + '/goods/list').then(res=>res.data).then(res=>{
                console.log(res);
            })
        },

        loadPost() {
            this.$axios.post(this.$httpUrl + '/goods/listPage', {
                pageSize: this.pageSize,
                pageNum: this.pageNum,
                param:{
                    name: this.name,
                }
            }).then(res=>res.data).then(res=>{
                console.log(res.code);
                if(res.code==200) {
                    this.tableData = res.data;
                    this.total = res.total;
                } else {
                    alert("获取数据失败");
                }
            })
        },

        loadStorage() {
            this.$axios.get(this.$httpUrl + '/storage/list').then(res=>res.data).then(res=>{
                console.log(res.code);
                if(res.code==200) {
                    this.storageData = res.data;
                } else {
                    alert("获取仓库数据失败");
                }
            })
        },

        loadGoodsType() {
            this.$axios.get(this.$httpUrl + '/goodstype/list').then(res=>res.data).then(res=>{
                console.log(res.code);
                if(res.code==200) {
                    this.goodsTypeData = res.data;
                } else {
                    alert("获取货物类型数据失败");
                }
            })
        },

        formatStorage(row) {
            let temp = this.storageData.find(item=>{
                return item.id == row.storage;
            });
            return temp && temp.name;
        },

        formatGoodsType(row) {
            let temp = this.goodsTypeData.find(item=>{
                return item.id == row.goodstype;
            });
            return temp && temp.name;
        },

        handleSizeChange(val) {
            console.log(`每页 ${val} 条`);
            this.pageNum = 1;
            this.pageSize = val;
            this.loadPost();
        },

        handleCurrentChange(val) {
            console.log(`当前页: ${val}`);
            this.pageNum = val;
            this.loadPost();
        },

        resetParam() {
            this.name='';
            this.note='';
        },

        resetForm() {
            this.$refs.form.resetFields();
        },

        add() {
            this.dialogVisible = true;
            this.$nextTick(()=>{
                this.resetForm();
                this.form.id = '';
            })
        },

        doSave() {
            this.$axios.post(this.$httpUrl + '/goods/' + this.saveOrModify, this.form).then(res=>res.data).then(res=>{
                console.log(res.code);
                if(res.code==200) {
                    this.$message({
                        message: '更新成功',
                        type: 'success'
                    });
                    this.dialogVisible = false;
                    this.loadPost();
                } else {
                    this.$message({
                        message: '更新失败',
                        type: 'fail'
                    });
                }
            });
            console.log('success submit!');
        },

        save() {
            this.$refs.form.validate((valid) => {
                if(this.form.id) {
                    this.saveOrModify = 'modify';
                    this.doSave();
                } else {
                    if(valid) {
                        this.saveOrModify = 'save';
                        this.doSave();
                    } else {
                        console.log('error submit!');
                        return false;
                    }
                }
            });
        },

        modify(row) {
            this.dialogVisible = true;
            this.$nextTick(()=>{
                this.form.id = row.id;
                this.form.name = row.name+'';
                this.form.goodstype = row.goodstype+'';
                this.form.storage = row.storage+'';
                this.form.count = row.count;
                this.form.note = row.note+'';
            })
        },

        del(id) {
            this.$axios.get(this.$httpUrl + '/goods/delete?id=' + id).then(res=>res.data).then(res=>{
                console.log(res.code);
                if(res.code==200) {
                    this.$message({
                        message: '删除成功',
                        type: 'success'
                    });
                    this.dialogVisible = false;
                    this.loadPost();
                } else {
                    this.$message({
                        message: '删除失败',
                        type: 'fail'
                    });
                }
            });
            console.log('success submit!');
        }
    },

    beforeMount() {
        this.loadStorage();
        this.loadGoodsType();
        this.loadPost();
    }
}
</script>

<style scoped>

</style>