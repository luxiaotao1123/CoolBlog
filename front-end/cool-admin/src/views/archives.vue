<template>
  <div class="archives">
    <h2>归档</h2>
    <div class="archive" v-for="(item, key) in archives" :key="item.index">
      <h4>{{key}}</h4>
      <div v-for="(year, key) of item"  
           :key="year.key" class="year-box">
        <ul v-for="(month,key) of year" :key="month.key"  class="archives-month-ul">
          <h5>{{key}} 月</h5>
          <li class="post-archive" v-for="day in month" 
              :key="day.key">
            <span class="triangle"> </span>
                    <span><router-link :to="{ name: 'blog', params: { id: day.blogid }}"> {{ day.title }}</router-link></span>
                </li>
            </ul>
        
          </div>
      </div>
  </div>
</template>
<script>
import service from '../utils/service.js'
export default {
  data () {
    return {
      archives: ''
    }
  },
  methods: {
    initData () {
      let that = this
      service.get('/api/archives')
        .then(function (response) {
        //   console.log(response)
          if (response.status === 200 || response.data.code === 200) {
            that.archives = response.data.archives
            console.log(that.archives)
          } else {
            console.log('错误！')
          }
        })
        .catch(function (error) {
          console.log(error)
        })
    }
  },
  mounted () {
    this.initData()
  }
}
</script>

<style scoped>
.archives{
   padding-top: 50px;
    display: block;
    max-width: 800px;
    margin: 0 auto;
    padding-right: 20px;
    padding-left: 40px;
    background-color: #ffffff;
  color: #5d686f;
   margin-bottom: 40px;
    margin-top: 50px;
}
.triangle {
  position: relative;
  display: inline-block;
  width: 8px;
  height: 8px;
  overflow: hidden;
}
.triangle:after {
  content: '';
  position: absolute;
  left: 2px;
  display: inline-block;
  border-top: 4px solid transparent;
  border-right: 4px solid transparent;
  border-bottom: 4px solid transparent;
  border-left: 4px solid #000;
}
.year-box{
    border-bottom: 1px solid #eef2f8;
}
.archives-month-ul{
    margin: 0 0 .5em;
    font-size: 16px;
    line-height: 28px;
    letter-spacing: 1px;
    -webkit-font-smoothing: subpixel-antialiased;
    -webkit-padding-start: 40px;
        -webkit-margin-before: 1em;
    -webkit-margin-after: 1em;
    -webkit-margin-start: 0px;
    -webkit-margin-end: 0px;
}
.archives h4{
font-size: 18px
}
.archives-month-ul h5{
        margin: 1em 0 .5em;
            letter-spacing: -.04em;
    font-weight: 700;
    line-height: 1.45em;
    font-size: 17px
}
.archives-month-ul li{
  font-family: "Open Sans",sans-serif;
}
.archives-month-ul a{
        margin-left: 10px;
    color: #5d686f;
    text-decoration: none;
    font-family: "Open Sans",sans-serif,微软雅黑;
}
.archives-month-ul a:hover{
    text-decoration: underline;
}
</style>
