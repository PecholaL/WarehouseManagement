<template>
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
</template>

<script>
export default {
    name: "WmsMain",
    data() {
        return {
            tableData: []
        }
    },
    methods: {
        loadGet() {
            this.$axios.get(this.$httpUrl + '/user/list').then(res=>res.data).then(res=>{
                console.log(res);
            })
        },
        loadPost() {
            this.$axios.post(this.$httpUrl + '/user/listP', {}).then(res=>res.data).then(res=>{
                console.log(res.code);
                if(res.code==200) {
                    this.tableData = res.data;
                } else {
                    alert("获取数据失败");
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