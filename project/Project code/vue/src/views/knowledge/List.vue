<template>
  <div>
    <!--搜索表单-->
    <div style="margin-bottom: 20px" >
      <el-input style="width: 240px" placeholder="请输入文章名称" v-model="params.username"></el-input>
<!--      <el-input style="width: 240px; margin-left: 5px" placeholder="请输入文章内容" v-model="params.phone"></el-input>-->
      <el-input style="width: 240px; margin-left: 5px" placeholder="请输入文章简介" v-model="params.email"></el-input>
      <el-button style="margin-left: 5px" type="primary" @click="load"><i class="el-icon-search"></i>搜索</el-button>
      <el-button style="margin-left: 5px" type="warning" @click="reset"><i class="el-icon-refresh"></i>重置</el-button>
    </div>

    <el-table :data="tableData" stripe>
      <el-table-column prop="id" label="编号" width="80"></el-table-column>
      <el-table-column label="文章名称">
        <template v-slot="scope">
          <el-button @click="model_code(scope.row.path)" type="text">{{scope.row.username}}</el-button>
        </template>
      </el-table-column>
      <el-table-column prop="phone" label="文章封面">
        <template v-slot="scope">
          <el-image :src="scope.row.phone" :preview-src-list="[scope.row.phone]"></el-image>
        </template>
      </el-table-column>

      <el-table-column prop="email" label="文章简介"></el-table-column>
      <el-table-column prop="createtime" label="创建时间"></el-table-column>
      <el-table-column prop="updatetime" label="更新时间"></el-table-column>

      <el-table-column label="审核状态" width="230">
        <template v-slot="scope">
          <el-switch
              v-model="scope.row.status"
              @change="changeStatus(scope.row)"
              active-color="#13ce66"
              inactive-color="#ff4949">
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column label="操作" width="230">
        <template v-slot="scope">
<!--    style="margin-left: 5px本来是为了删除和编辑间隔开，但是只能放成上下"      scope.row就是当前行数据-->
          <el-button type="primary" @click="$router.push('/editKnowledge?id=' + scope.row.id)">编辑</el-button>
          <el-popconfirm

              title="您确定删除内容吗？"
              @confirm="del(scope.row.id)"
          >
            <el-button style="margin-left: 5px" type="danger" slot="reference">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <!--分页-->
    <div style="margin-top: 20px">
      <el-pagination
          background
          :current-page="params.pageNum"
          :page-size="params.pageSize"
          layout="prev, pager, next"
          @current-change="handleCurrentChange"
          :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";
import Cookies from 'js-cookie';
export default {
  name: 'KnowledgeList',
  data(){
    return{
      admin: Cookies.get('knowledge') ? JSON.parse(Cookies.get('knowledge')) : {},
      tableData:[],
      total:0,
      form: {},
      dialogFormVisible:false,
      params: {
        pageNum: 1,
        pageSize: 4,
        username: '',
        phone: '',
        email: '',
        url:''
      },
      rules: {
      }
    }
  },
  created() {
    this.load()
  },
  methods: {/*将数据库传到前端*/
    changeStatus(row) {
      if(this.admin.id === row.id && !row.status) {
        row.status = true
        this.$notify.success("该操作不合法！")
        return
      }
      request.put('/knowledge/update',row).then(res => {
        if(res.code === '200') {
          this.$notify.success('操作成功')
          this.load()
        }else{
          this.$notify.error(res.msg)
        }
      })
    },
    model_code(lf){
      console.log("您好！");
      window.open(lf);
    },

    handleChangePass(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogFormVisible = true
    },
    savePass() { //进行校验
      this.$refs['formRef'].validate((valid) => {
        if (valid) {
          request.put('/knowledge/password',this.form).then(res => {
            if(res.code === '200'){
              this.$notify.success("修改成功")
              if (this.form.id === this.admin.id) {//当前修改的用户id为当前登录的管理员id，那么修改成功后要重新登录
                Cookies.remove('knowledge')
                this.$router.push('/login')
              } else {
                this.load() //刷新界面，修改自己的密码保护机制会生效，会重新登录，改其他管理员的则不会弹出登录窗口
                this.dialogFormVisible = false//关闭弹窗
              }
            }else{
              this.$notify.error("修改失败")
            }
          })
        }
      })
    },
    load() {
      request.get('/knowledge/page',{
        params: this.params
      }).then(res => {
        if(res.code === "200"){
          this.tableData = res.data.list
          this.total = res.data.total
        }
      })
    },

    reset() {
      this.params = {
        pageNum: 1,
        pageSize: 10,
        username: '',
        phone: '',
        email: ''
      }
      this.load()
    },
    handleCurrentChange(pageNum) {
      //点击按钮触发分页
      this.params.pageNum = pageNum
      this.load()
    },
    del(id) {
      request.delete("/knowledge/delete/" + id).then(res => {
        if(res.code === "200"){
          this.$notify.success('删除成功')
          this.load()
        }else{
          this.$notify.error(res.msg)
        }
      })
    }
  }
}
</script>

<style scoped>

</style>