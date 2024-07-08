import Vue from "vue";
import Vuex from "vuex";
import router, {resetRouter} from "../router"
Vue.use(Vuex);

function addNewRoute(menuList) {
    let routes = router.options.routes;
    routes.forEach(routeItem=>{
        if(routeItem.path=='/main') {
            menuList.forEach(menu=>{
                let childRoute = {
                    path: '/main/'+menu.menuclick,
                    name: menu.menuname,
                    meta: {
                        title: menu.menuname
                    },
                    component: ()=>import('../'+menu.menucomponent)
                };
                routeItem.children.push(childRoute);
            })
        }
    });
    resetRouter();
    routes.forEach(route=>{
        router.addRoute(route);
    })
}


// export default new Vuex.Store({
const store = new Vuex.Store({
    state: {
        menu: []
    },
    mutations: {
        setMenu(state, menuList) {
            // 传入用于动态生成页面的menu参数
            state.menu = menuList;
            // 设置动态路由
            addNewRoute(menuList);
        }
    },
    getters: {
        getMenu(state) {
            return state.menu;
        }
    }
});

export default store;
