<template>
<div class="container">
   <Form   ref="blogFormItem" :model="blogFormItem" :label-width="80" :rules="ruleValidate" >
     <FormItem label="Title:" prop="title">
       <Row>
         <Col  :xs="20" :sm="16" :md="12" :lg="8">
            <Input type="text" v-model="blogFormItem.title"></Input>
          </Col>
       </Row>
    </FormItem>
    <FormItem label="Label" prop="label">
      <Row>
           <Col  :xs="20" :sm="16" :md="12" :lg="8">
              <Select v-model="blogFormItem.label" placeholder="Select your label">
                        <Option value="js">Javascript</Option>
                        <Option value="java">Java</Option>
                        <Option value="Python">Python</Option>
              </Select>
       </Col>
      </Row>
    </FormItem>
    <FormItem label="Content" prop="content">
      <Row>
           <Col  :xs="20" :sm="16" :md="16" :lg="20">
            <quill-editor ref="myTextEditor"
                          v-model="content"
                          :config="editorOption"
                          @change="onEditorChange($event)"
                          >
            </quill-editor>
          </Col>
      </Row>
    </FormItem>
    <FormItem>
            <Button type="primary" @click="handleSubmit('blogFormItem')">Submit</Button>
            <Button type="ghost" @click="handleReset('blogFormItem')" style="margin-left: 8px">Reset</Button>
    </FormItem>
   </Form>
</div>
</template> 
<script>
import { quillEditor } from 'vue-quill-editor'
import service from '../utils/service.js'
export default {
  components: {
    quillEditor
  },
  data () {
    return {
      content: '',
      editorOption: {
        // something config
      },
      blogFormItem: {
        title: '',
        label: '',
        content: ''
      },
      ruleValidate: {
        title: [
            { required: true, message: '别忘记写标题啊！', trigger: 'blur' }
        ],
        label: [
            { required: true, message: '别忘记选标签啊！', trigger: 'blur' }
        ],
        content: [
            { required: true, message: '别忘记写文章啊！', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    onEditorChange ({editor, html, text}) {
      this.blogFormItem.content = html
    },
    initBlog () {
      console.log(this.$route.params.blogid)
      let blogid = this.$route.params.blogid
      let that = this
      service.get('/api/admin/blog/' + blogid)
      .then(function (responce) {
        console.log(responce)
        that.blogFormItem.title = responce.data.blog.title
        that.blogFormItem.label = responce.data.blog.label
        that.blogFormItem.blogid = responce.data.blog.blogid
        that.blogFormItem.userid = responce.data.blog.userid
        that.blogFormItem.content = responce.data.blog.content
        that.content = responce.data.blog.content
      })
      .catch(function (error) {
        console.log(error)
      })
    },
    handleReset (name) {
      this.$refs[name].resetFields()
    },
    handleSubmit (name) {
      this.$refs[name].validate(valid => {
        console.log(this.blogFormItem)
        let that = this
        let item = this.blogFormItem
        // let y = JSON.stringify(item)
        // console.log(x + data)
        console.log(item)
        if (valid) {
          service({
            url: '/api/admin/blog',
            method: 'put',
            contentType: 'application/json',
            data: item
          })
          .then(function (response) {
            console.log(response)
            if (response.status === 200 || response.data.code === 200) {
              that.$Message.success('提交成功')
              that.$forceUpdate()
            } else {
              that.$Message.error('提交失败了! ')
            }
          })
          .catch(function (error) {
            console.log(error)
          })
        } else {
          this.$Message.error('失败')
        }
      })
    }
  },
  mounted () {
    this.initBlog()
  },
  watch: {
    '$route': 'initBlog'
  }
}
</script>

