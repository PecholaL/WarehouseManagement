<template>
    <el-menu 
        style="height: 500%;" 
        default-active="/Home" 
        active-text-color="darkorange" 
        :collapse="isCollapse" 
        :collapse-transition="false"
    >
        <el-menu-item index="/Home">
            <i class="el-icon-house"></i>
            <span slot="title">首页</span>
        </el-menu-item>
        <el-menu-item index="/1">
            <i class="el-icon-box"></i>
            <span slot="title">1</span>
        </el-menu-item>
        <el-menu-item index="/2">
            <i class="el-icon-smoking"></i>
            <span slot="title">2</span>
        </el-menu-item>
    </el-menu>
</template>

<script>
export default {
    name: "WmsAside", 
    data() {
        return {
        }
    },
    props: {
        isCollapse: Boolean
    },
}
</script>

<style scoped>

</style>