/**
 * http请求全局拦截器。用于拦截所有http请求中是否带有token信息和token是否已经超时。
 * 如果为否，将跳转到登录界面进行登录重新校验。
 * 如果为是，将带上用户token信息进行请求
 */

import axios from './js/axios/axios';
import router from './js/common/http-router';

//axios 配置
axios.defaults.timeout = 6000;
axios.defaults.baseURL = 'http://localhost/login.html';

// http request 拦截器
axios.interceptors.request.use(
  config => {
    if (localStorage.token) { //判断token是否存在
      config.headers.Authorization = localStorage.token;  //将token设置成请求头
    }
    return config;
  },
  err => {
    return Promise.reject(err);
  }
);

// http response 拦截器
axios.interceptors.response.use(
  response => {
    if (response.data.errno === 999) {
      router.replace('/');
      console.log("token过期");
    }
    return response;
  },
  error => {
    return Promise.reject(error);
  }
);
export default axios;
