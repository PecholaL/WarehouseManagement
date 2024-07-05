import VueRouter from "vue-router";

const routes = [
    {
        path: '/',
        name: 'login',
        component: ()=>import('../components/WmsLogin')
    }
]

const router = VueRouter({
    mode: 'history',
    routes
})

export default router;