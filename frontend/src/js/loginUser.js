import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    loginid: ""
  },
  getters: {
    getid(state) {
      return state.loginid;
    }
  },
  mutations: {
    setLoginId(state, payload) {
      state.loginid = payload.id;
    }
  },
  actions: {
    setLoginId(store, userid) {
      store.commit("setLoginId", { id: userid });
    }
  }
});
