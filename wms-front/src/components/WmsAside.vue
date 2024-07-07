<template>
    <el-menu 
        style="height: 500%;" 
        default-active="/Home" 
        active-text-color="darkorange" 
        :collapse="isCollapse" 
        :collapse-transition="false"
        router
    >
        <el-menu-item index="/index">
            <i class="el-icon-house"></i>
            <span slot="title">首页</span>
        </el-menu-item>
        <el-menu-item :index="'/index/'+item.menuClick" v-for="(item,i) in menu" :key="i">
            <i :class="item.menuIcon"></i>
            <span slot="title">{{ item.menuName }}</span>
        </el-menu-item>
    </el-menu>
</template>

<script>
export default {
    name: "WmsAside", 
    data() {
        return {
            menu: [
                {
                    menuClick: 'admin',
                    menuName: '管理员管理',
                    menuIcon: 'el-icon-s-custom'
                },
                {
                    menuClick: 'user',
                    menuName: '用户管理',
                    menuIcon: 'el-icon-user-solid'
                }
            ]
        }
    },
    props: {
        isCollapse: Boolean
    },
}
</script>

<style scoped>

</style>