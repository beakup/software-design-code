import axios from 'axios'
import router from "@/router";
import Cookies from 'js-cookie'

//import config from "vue/src/core/config"

const request = axios.create({
    baseURL:'http://localhost:9090/api',
    timeout: 5000
})
//request拦截器
request.interceptors.request.use(config => {
    config.headers['Content-Type'] = 'application/json;charset=utf-8';

    const adminJson = Cookies.get('userEdge');
    if(adminJson) {
        //设置请求头
        config.headers['token'] = JSON.parse(adminJson).token;
    }
    return config
},error => {
    return Promise.reject(error)
});

request.interceptors.response.use(
    response => {
        let res = response.data;

        if (typeof res === 'string') {
            res = res? JSON.parse(res) : res
        }
        if(res.code === '401') {
            router.push('/userLogin')
        }
        return res;
    },
    error => {
        console.log('err' + error)
        return Promise.reject(error)
    }
)

export default request