import VueRouter from "vue-router";
import WmsLogin from "../WmsLogin.vue";
import WmsIndex from "../WmsIndex.vue";
import WmsHome from "../WmsMain/WmsHome.vue";
import WmsMain from "../WmsMain/WmsMain.vue";

const routes = [
    {
        path: '/',
        name: 'login',
        component: WmsLogin
    },
    {
        path: '/main',
        component: WmsIndex,
        children: [
            {
                path: '/',
                meta: {
                    title: "首页"
                },
                component: WmsHome,
            },
            {
                path: 'home',
                name: 'home',
                meta: {
                    title: "人员管理"
                },
                component: WmsMain,
            }
        ]
    }
];

const router = new VueRouter({
    mode: 'history',
    routes
});

export function resetRouter() {
    router.matcher = new VueRouter({
        mode: 'history',
        routes: []
    }).matcher;
}

const VueRouterPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push (to) {
    return VueRouterPush.call(this, to).catch(err=>err)
};

export default router;