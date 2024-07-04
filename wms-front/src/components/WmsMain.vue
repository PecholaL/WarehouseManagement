<template>
    <div>
        <div style="margin-bottom: 1%;" align="left">
            <el-input v-model="name" 
                size="small" placeholder="请输入姓名" 
                suffix-icon="el-icon-search" style="width: 15%;" 
                @keyup.enter.native="loadPost">
            </el-input>
            <el-select v-model="sex" filterable placeholder="请选择性别" 
                size="small" style="margin-left: 5px; width: 10%">
                <el-option
                    v-for="item in sexes" 
                    :key="item.value" 
                    :label="item.label" 
                    :value="item.value">
                </el-option>
            </el-select>
            <el-button size="mini" type="primary" style="margin-left: 5px;" @click="loadPost">查询</el-button>
            <el-button size="mini" type="success" style="margin-left: 5px" @click="resetParam">重置</el-button>
            <el-button size="mini" type="primary" style="margin-left: 5px;" @click="add">新增</el-button>
        </div>
        <el-table :data="tableData"
            :header-cell-style="{background:'#ffcc99', color:'#555'}"

        >
            <el-table-column prop="id" label="ID" width="80">
            </el-table-column>
            <el-table-column prop="no" label="账号" width="120">
            </el-table-column>
            <el-table-column prop="name" label="姓名" width="120">
            </el-table-column>
            <el-table-column prop="sex" label="性别" width="80">
                <template slot-scope="scope">
                    <el-tag
                        :type="scope.row.sex === 1 ? 'primary' : 'success' "
                        disable-transition>{{ scope.row.sex === 1 ? '男' : '女' }}</el-tag>
                </template>
            </el-table-column>
            <el-table-column prop="age" label="年龄" width="80">
            </el-table-column>
            <el-table-column prop="phone" label="电话" width="180">
            </el-table-column>
            <el-table-column prop="roleId" label="角色" width="120">
                <template slot-scope="scope">
                    <el-tag
                        :type="scope.row.roleId === 0 ? 'danger' : (scope.row.roleId === 1 ? 'primary' : 'success') "
                        disable-transition>{{ scope.row.roleId === 0 ? '超级管理员' : (scope.row.roleId === 1 ? '管理员' : '用户') }}</el-tag>
                </template>
            </el-table-column>
            <el-table-column prop="operate" label="操作" width="150">
                <el-button size="small" type="success">编辑</el-button>
                <el-button size="small" type="danger">删除</el-button>
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
            <el-form ref="form" :model="form" label-width="80px">
                <el-form-item label="账号">
                    <el-col :span="18">
                        <el-input v-model="form.no"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="密码">
                    <el-col :span="18">
                        <el-input v-model="form.password"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="姓名">
                    <el-col :span="18">
                        <el-input v-model="form.name"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="性别">
                    <el-radio-group v-model="form.sex">
                        <el-radio label="1">男</el-radio>
                        <el-radio label="0">女</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="年龄">
                    <el-col :span="18">
                        <el-input v-model="form.age"></el-input>
                    </el-col>
                </el-form-item>
                <el-form-item label="电话">
                    <el-col :span="18">
                        <el-input v-model="form.phone"></el-input>
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
    name: "WmsMain",
    data() {
        return {
            tableData: [],
            pageSize: 5,
            pageNum: 1,
            total: 0,
            name: '',
            sex: '',
            sexes: [
                {
                    value: '1',
                    label: '男'
                },
                {
                    value: '0',
                    label: '女'
                }
            ],
            dialogVisible: false,
            form: {
                no: '',
                password: '',
                name: '',
                sex: '0',
                age: '',
                phone: '',
                roleId: '2'
            }
        }
    },
    methods: {
        loadGet() {
            this.$axios.get(this.$httpUrl + '/user/list').then(res=>res.data).then(res=>{
                console.log(res);
            })
        },
        loadPost() {
            this.$axios.post(this.$httpUrl + '/user/listPage', {
                pageSize: this.pageSize,
                pageNum: this.pageNum,
                param:{
                    name: this.name,
                    sex: this.sex
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
            this.sex='';
        },
        add() {
            this.dialogVisible = true;
        },
        save() {
            this.$axios.post(this.$httpUrl + '/user/save', this.form).then(res=>res.data).then(res=>{
                console.log(res.code);
                if(res.code==200) {
                    this.$message({
                        message: '添加成功',
                        type: 'success'
                    });
                    this.dialogVisible = false;
                    this.loadPost();
                } else {
                    this.$message({
                        message: '添加失败',
                        type: 'success'
                    });
                }
            })
        }
    },
    beforeMount() {
        // this.loadGet();
        this.loadPost();
    }
}
</script>

<style scoped>

</style>