import VueRouter from "vue-router";
import WmsLogin from "../WmsLogin.vue";
import WmsIndex from "../WmsIndex.vue";
import WmsHome from "../WmsIndex/WmsHome.vue";
import WmsMain from "../WmsIndex/WmsMain.vue";

const routes = [
    {
        path: '/',
        name: 'login',
        component: WmsLogin
    },
    {
        path: '/WmsIndex',
        component: WmsIndex,
        children: [
            {
                path: '/',
                name: 'index',
                meta: {
                    title: "首页"
                },
                component: WmsMain,
            },
            {
                path: 'WmsHome',
                name: 'home',
                meta: {
                    title: "人员管理"
                },
                component: WmsHome,
            }
        ]
    }
]

const router = new VueRouter({
    mode: 'history',
    routes
})
const VueRouterPush = VueRouter.prototype.push
VueRouter.prototype.push = function push (to) {
    return VueRouterPush.call(this, to).catch(err=>err)
}

export default router;