<template>
    <div class="global-footer">
        <div class="footer-box">
            <p> Copyright © 2017 CoolBlog Powered by CoolBoy </p>
        </div>
    </div>    
</template>
  

<style scoped>
.footer-box{
    width:100%;
    margin: 0 auto
}
.footer-box p{
    text-align: center;
    padding-bottom: 20px;
    padding-top: 10px
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
  

