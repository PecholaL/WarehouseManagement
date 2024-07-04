<template>
    <div>
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
            total: 0
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
                pageSize:this.pageSize,
                pageNum:this.pageNum
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