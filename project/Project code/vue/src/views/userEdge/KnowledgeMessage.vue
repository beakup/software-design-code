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

    <el-table :data="fliterTableData" stripe>
      <el-table-column prop="id" label="编号" width="80"></el-table-column>
      <el-table-column label="文章名称">
        <template v-slot="scope">
          <el-button @click="handleModelCode(scope.row.path)" type="text">{{scope.row.username}}</el-button>
          <!--          <a href="<%= scope.row.phone %>">{{scope.row.username}}</a>-->
        </template>
      </el-table-column>
      <el-table-column prop="phone" label="文章封面">
        <template v-slot="scope">
          <el-image :src="scope.row.phone" :preview-src-list="[scope.row.phone]"></el-image>
        </template>
      </el-table-column>
<!--      <el-table-column prop="phone" label="文章内容"></el-table-column>-->
      <el-table-column prop="email" label="文章备注"></el-table-column>
      <el-table-column prop="createtime" label="创建时间"></el-table-column>
      <el-table-column prop="updatetime" label="更新时间"></el-table-column>

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
import request from "@/utils/userRequest";
import Cookies from 'js-cookie';
export default {
  name: 'KnowledgeList',
  data(){
    return{
      userEdge: Cookies.get('knowledge') ? JSON.parse(Cookies.get('knowledge')) : {},
      tableData:[],
      total:0,
      form: {},
      dialogFormVisible:false,
      model_code:false,
      params: {
        pageNum: 1,
        pageSize: 4,
        username: '',
        phone: '',
        email: '',
        url:''
      },
      rules: {
        newPass: [
          {required: true, message: '请输入新密码', trigger: 'blur'},
          { min: 3,max: 12,message: '长度为3-12个字符', trigger: 'blur'}
        ],
      }
    }
  },
  computed: {
    fliterTableData() {
      return this.tableData.filter(item => item.status == 1);
      // 此后要调整total的值，否则禁用的数据不会向前填充
    }
  },
  created() {
    this.load()
    this.filteredTotal = this.tableData.filter(item => item.status == 1).length;
  },
  methods: {/*将数据库传到前端*/
    handleModelCode(lf){
      console.log("123");
      window.open(lf);
    },
    handleChangePass(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogFormVisible = true
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
        email: '',
        total:''
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