import Vue from "vue";
import Vuex from "vuex";
Vue.use(Vuex);


export default new Vuex.Store({
    state:{
        menu: []
    },
    mutations:{
        setMenu(state, menuList) {
            state.menu = menuList;
        }
    },
    getters:{
        getMenu(state) {
            return state.menu;
        }
    }
})
