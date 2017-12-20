<template>
<div class="article-cards">
      <div class="article-card" v-for="(item, key) in list" :key="item.key">
            <div class="card-box">
               <div class="blog-img">
                   <router-link :to="{ name: 'blog', params: { id: item.blogid }}">
                   <img :src='item.thumpreview' > 
                   </router-link>
               </div>
               <div class="article-title">
                   <div class="title-box">
                    <span class="title-white"><router-link :to="{ name: 'blog', params: { id: item.blogid }}"> {{item.title}}</router-link> </span>
                    <span class="title-date">{{item.updatetime}}</span>
                   </div>
               </div>
               <div style="clear:both"></div>
               <div class="article-abstract">
                   <div>{{item.summary}}</div>
               </div>
               <div class="article-info">
                   <div class="face" v-if="item.userid==5" @click="go(item.userid)">
                       <img width="50" height="50" src="https://tvax3.sinaimg.cn/crop.0.19.670.670.180/006TYhdCly8fi3z75745fj30j60j6aeb.jpg" alt="Tom">
                       <span style="padding-left:20px">Tom</span>
                   </div>
                   <div class="face" v-if="item.userid==4" @click="go(item.userid)">
                       <img width="50" height="50" src="https://sfault-avatar.b0.upaiyun.com/284/938/2849383166-59fbc62406de4_huge256" alt="大叔一枝花">
                       <span style="padding-left:20px">Vincent</span>
                   </div>
                   <div class="tags">
                       <span>{{item.label}}</span>
                   </div>
               </div>
            </div>
        </div>
</div>
</template>
<script>
export default {
  data () {
    return {}
  },
  props: ['list'],
  methods: {
    go (id) {
      this.$router.push({name: 'author', params: {id: id}})
    }
  }
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
    box-shadow: rgba(0, 0, 0, 0.12) 0px 1px 6px, rgba(0, 0, 0, 0.12) 0px 1px 4px;
    border-radius: 2px;
    z-index: 1;
   max-width: 960px;
    display: flex;
 justify-content:center;
 margin: 8px;
 margin-bottom: 47.5px
}
.article-card:hover{
        box-shadow: 0 3px 10px rgba(0,0,0,.156863),0 3px 10px rgba(0,0,0,.227451)!important;
}
.card-box{
    width: 100%;
    position: relative;
    overflow: hidden;
}
.article-card .blog-img a>img{
    width:100% ;
    height: 326px;
}
.article-title{
    position: absolute;    
   top:230px;
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
.article-title .title-box .title-white a{
    color: rgba(255, 255, 255, 0.87);
    text-overflow:ellipsis;
 overflow: hidden;
 white-space: nowrap;
display: block;

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
    border-bottom: 1px solid #eef2f8
}
.article-abstract div{
max-height: 45px;
text-overflow:ellipsis;
 overflow: hidden;
 white-space: nowrap;
}
.article-info{
     background-color: rgb(255, 255, 255);
     padding: 10px;
}
.article-info .face{
    width: 160px;
    float: left;
    height: 65px;
    cursor: pointer;
}
.article-info .face>img{
    border-radius: 50%;
    /* border: 1px solid #adadad */
}
.tags{
    float: right ;
    padding-top: 20px;
    padding-right: 10px
}
.tags span {
    color: #969696;
    font-size: 14px;
    cursor: pointer;
}
@media screen and (max-width: 1024px)  {
    .article-cards{
        width: 90%;
        margin: 0px auto
    }
    .article-card{
        /* width: 100%; */
        overflow: hidden;   
    }
   
}
@media screen and (max-width: 420px)  {
 .article-card .blog-img>img{
  height: 230px;
transition: all 1s ease;
    }
    .article-title{
            top: 135px;
            transition: all 1s ease;
    }
.article-card .blog-img a>img{
    height: 231px;
}
.article-title .title-box .title-white a{
       font-size: 17px;
}
}
</style>


