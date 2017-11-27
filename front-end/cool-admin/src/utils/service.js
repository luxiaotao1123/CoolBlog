import axios from 'axios'
import store from '../store/index.js'
// import QS from 'querystring'

// 创建axios实例
const service = axios.create({
  baseURL: '', // api的base_url
  timeout: 5000                  // 请求超时时间
})
// request拦截器

// let token = window.sessionStorage.getItem('token')
// let token = store.state.token
// console.log(token)
// service.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;'
service.defaults.headers.post['Content-Type'] = 'application/json; charset=UTF-8'

// 设置
service.interceptors.request.use(config => {
    // Do something before request is sent
  if (store.state.token) {
    let token = store.state.token
    config.headers['token'] = token  // 让每个请求携带token--['X-Token']为自定义key 请根据实际情况自行修改
    // console.log(token)
  }
  return config
}, error => {
    // Do something with request error
  console.log(error) // for debug
  Promise.reject(error)
})

export default service
