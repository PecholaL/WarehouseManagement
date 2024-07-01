<template>
    <el-container style="height: 100%; border: 1px solid #eee">
        <el-aside width="200px" style="background-color: rgb(238, 241, 246); height: 100%;">
            <WmsAside></WmsAside>
        </el-aside>

        <el-container style="height: 100%;">
            <el-header style="text-align: right; font-size: 12px; height: 100%;">
                <WmsHeader></WmsHeader>
            </el-header>

            <el-main style="height: 100%;">
                <WmsMain></WmsMain>
            </el-main>
        </el-container>
</el-container>
</template>

<style>
    .el-header {
        background-color: #daa56e;
        color: #000000;
        line-height: 60px;
    }

    .el-main{
        padding: 5px;
    }

    .el-aside {
        color: #333;
    }
</style>

<script>
import WmsAside from "./WmsAside.vue";
import WmsHeader from "./WmsHeader.vue";
import WmsMain from "./WmsMain.vue";

export default {
    name: "WmsIndex",
    components: {WmsAside,WmsHeader,WmsMain}
};
</script>