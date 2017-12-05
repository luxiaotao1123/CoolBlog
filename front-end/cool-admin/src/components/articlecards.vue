<template>
<div class="article-cards">
      <div class="article-card" v-for="(item, key) in list" :key="item.key">
            <div class="card-box">
               <div class="blog-img">
                   <img src="http://hbfile.b0.upaiyun.com/img/home/banner/9165d863f69677e01f154456f4203d40fc8ce3dc113f4e" >
               </div>
               <div class="article-title">
                   <div class="title-box">
                    <span class="title-white">{{item.title}} </span>
                    <span class="title-date">{{item.updatetime}}</span>
                   </div>
               </div>
               <div style="clear:both"></div>
               <div class="article-abstract">
                   <div>abstract</div>
               </div>
               <div></div>
            </div>
        </div>
</div>
</template>
<script>
export default {
  data () {
    return {}
  },
  props: ['list']
}
</script>
<style scoped>
.article-cards{
 display: flex;
   justify-content:center;
   flex-direction:column;
   width: 980px;
}
.article-card{
-webkit-tap-highlight-color: rgba(0, 0, 0, 0);
    color: rgba(0, 0, 0, 0.87);
    background-color: rgb(255, 255, 255);
    transition: all 450ms cubic-bezier(0.23, 1, 0.32, 1) 0ms;
    box-sizing: border-box;
    font-family: -apple-system, system-ui, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", sans-serif;
    box-shadow: rgba(0, 0, 0, 0.12) 0px 1px 6px, rgba(0, 0, 0, 0.12) 0px 1px 4px;
    border-radius: 2px;
    z-index: 1;
   max-width: 960px;
    display: flex;
 justify-content:center;
 margin: 8px
}
.article-card:hover{
        box-shadow: 0 3px 10px rgba(0,0,0,.156863),0 3px 10px rgba(0,0,0,.227451)!important;
}
.card-box{
    width: 100%;
    position: relative;
}
.article-card .blog-img>img{
    width: 100%;
    height: 326px;
}
.article-title{
    position: absolute;    
    bottom: 58px;
    right: 0px;
    left: 0px;
    padding-top: 8px;
    background: rgba(0, 0, 0, 0.54)
}
.article-title .title-box{
    padding: 16px;
    position: relative;
}
.article-title .title-box .title-white {
font-size: 24px;
    color: rgba(255, 255, 255, 0.87);
    display: block;
    line-height: 36px;    
}
.article-title .title-box .title-date{
        font-size: 14px;
    color: rgba(255, 255, 255, 0.54);
    display: block;
}
.article-abstract{

    padding: 16px;
    font-size: 14px;
    color: rgba(0, 0, 0, 0.87);
    background-color: rgb(255, 255, 255);
    
}
</style>


