<template>
    <div>
        <div style="margin-bottom: 1%;" align="left">
            <el-input v-model="name" 
                size="small" placeholder="请输入货物名" 
                suffix-icon="el-icon-search" style="width: 15%;" 
                @keyup.enter.native="loadPost">
            </el-input>
            <el-select v-model="storage" style="margin-left: 5px;" size="small" placeholder="请选择仓库">
                <el-option
                    v-for="item in storageData"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id">
                </el-option>
            </el-select>
            <el-select v-model="goodstype" style="margin-left: 5px;" size="small" placeholder="请选择货物类型">
                <el-option
                    v-for="item in goodsTypeData"
                    :key="item.id"
                    :label="item.name"
                    :value="item.id">
                </el-option>
            </el-select>
            <el-button size="mini" type="primary" style="margin-left: 5px;" @click="loadPost">查询</el-button>
            <el-button size="mini" type="success" style="margin-left: 5px" @click="resetParam">重置</el-button>
        </div>
        <el-table
            :data="tableData"
            :header-cell-style="{background:'#ffcc99', color:'#555'}"
        >
            <el-table-column prop="id" label="ID" min-width="8%">
            </el-table-column>
            <el-table-column prop="goodsname" label="货物" min-width="20%">
            </el-table-column>
            <el-table-column prop="goodstypename" label="类型" min-width="15%">
            </el-table-column>
            <el-table-column prop="storagename" label="仓库" min-width="15%">
            </el-table-column>
            <el-table-column prop="count" label="数量" min-width="10%">
            </el-table-column>
            <el-table-column prop="adminname" label="操作人" min-width="15%">
            </el-table-column>
            <el-table-column prop="username" label="申请人" min-width="15%">
            </el-table-column>
            <el-table-column prop="createtime" label="操作时间" min-width="30%">
            </el-table-column>
            <el-table-column prop="note" label="备注" min-width="46%">
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
    </div>
</template>

<script>
export default {
    name: "WmsRecordManage",
    data() {
        return {
            user: JSON.parse(sessionStorage.getItem('CurUser')),
            storageData: [],
            goodsTypeData: [],
            tableData: [],
            pageSize: 5,
            pageNum: 1,
            total: 0,
            name: '',
            storage: '',
            goodstype: '',
            saveOrModify: '',
        }
    },
    methods: {
        loadPost() {
            this.$axios.post(this.$httpUrl + '/record/listPage', {
                pageSize: this.pageSize,
                pageNum: this.pageNum,
                param:{
                    name: this.name,
                    goodstype: this.goodstype+'',
                    storage: this.storage+'',
                    roleId: this.user.roleId+'',
                    userId: this.user.id+''
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

        resetParam() {
            this.name='';
            this.storage='';
            this.goodstype='';
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