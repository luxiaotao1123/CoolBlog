<template>
    <div class="post-header">
        <br>
        <br>
        <h1>{{blogInfo.title}}</h1>
        <div class="post-meta">
            <time>{{blogInfo.updatetime}}</time>
        </div>
        <div class="post-content" id='post-content'>
            <div class="image-view">
                <img :src="blogInfo.preview" alt="">
                
            </div>
            <div class='main-content' v-html="blogInfo.content"></div>
        </div>
    </div>
</template>
<script>
import service from '../utils/service.js'
export default {
  data () {
    return {
      blogInfo: {
        title: '',
        updatetime: '',
        content: ''
      }
    }
  },
  methods: {
    initblog () {
      let id = this.$route.params.id
    //   console.log(id)
      let that = this
      service.get('/api/blog/' + id)
      .then(function (responce) {
        // console.log(responce)
        if (responce.status === 200 || responce.data.code === 200) {
          that.blogInfo = responce.data.getBlog
          console.log(that.blogInfo)
        } else {
          that.$Message.error('获取失败! ')
        }
      })
      .catch(function (error) {
        console.log(error)
      })
    }
  },
  mounted () {
    this.initblog()
  },
  watch: {
    '$route': 'initblog'
  },
  beforeMount () {
    this.blogInfo = ''
  }
}
</script>
<style scoped >
.post-header{
    display: block;
    max-width: 800px;
    margin: 0 auto;
    padding-right: 20px;
    padding-left: 20px;
    background:#fff;
    margin-top: 10px;
    margin-bottom: 20px;
    font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
    width: 100%;
}
.image-view img{
    max-width: 100%;
    height: auto;
    padding-bottom: 20px
}
.post-meta{
line-height: 1.5em;
    font-family: "Open Sans",sans-serif;
    font-size: 13px;
    font-weight: 400;
    color: #9eabb3;
}
.post-content{
   text-rendering: optimizelegibility;
    letter-spacing: -.3px;
    /* font-family: Merriweather,serif; */
    color: #5d686f;
    margin-top: 20px;
    font-weight: 400;
    overflow: hidden;
}

.main-content{
     display: block;
     /* flex-direction:row; */
/* justify-content:center; */
/* align-items:center; */
   width: 90%;
    /* max-width: 960px; */
    min-width: 280px;
    /* width: 100px; */
    margin: 0 auto;
    position: relative;
    padding-right: 20px;
    padding-left: 20px;
    overflow: hidden;
}
.main-content img{
    max-width: 100% !important;
}
@media only screen and (min-width: 1024px){
    .post-header h1{
    font-size: 37px;
    }
    .post-content{
    font-size: 18px;
    width: 100%;
    }
    .main-content{
         width: 90%;
         max-width: 760px;
    }
}
@media only screen and (max-width: 1023px) and (min-width: 768px) {
    .post-header h1{
    font-size: 30px;
    width: 100%
    }
    .post-content{
    font-size: 17px;
    width: 100%;
    }
}
@media only screen and (max-width: 767px){
    h1{
        font-size: 25px;
    }
    .post-content{
    width: 100%;
    }
    .main-content{
        width: 100%;
    }
}
</style>
<style>
.main-content p img{
    max-width: 80% !important;
    margin-left: 10%
}
</style>
<style>
 pre{
    padding: 1em;
    border: none;
    overflow: auto;
    line-height: 1.45;
    max-height: 35em;
    /* position: relative; */
    display: block;
    overflow-x: auto;
    padding: 0.5em;
    color: #333;
    background: #e1e1e1;
    padding: 9.5px;
    margin: 0 0 10px;
    font-size: 13px;
    line-height: 1.42858;
    word-break: break-all;
    word-wrap: break-word;
    color: #333;
    border: 1px solid #ccc;
    border-radius: 4px;
    width:100%
}
@media only screen and (max-width: 1023px) and (min-width: 768px){
    pre{
         max-width: 600px;
     }
}
@media only screen and (max-width: 767px){
     pre{
         max-width: 360px;
         min-width: 280px;
     }
}

</style>


