<template>
<div>
    <b-navbar toggleable="lg" type="dark" variant="info" fixed="top">
            <b-navbar-toggle target="nav-text-collapse"></b-navbar-toggle>

            <b-navbar-brand to="dashboard">Parcel Delivery System</b-navbar-brand>


            <b-collapse id="nav-text-collapse" is-nav>
                <b-navbar-nav class="ml-auto">
                    <b-nav-text style="color:#fefefe">ยินดีต้อนรับคุณ <b>{{this.employeeData.name}}</b> เข้าสู่ระบบในฐานะ {{this.employeeData.employeePosition.position}} </b-nav-text>
                    <b-button @click="this.logout" size="sm" variant="danger" class="ml-4 my-2 my-sm-0" >ออกจากระบบ</b-button>
                </b-navbar-nav>
            </b-collapse>
    </b-navbar>
    <div class="container" style="margin-top:80px;margin-bottom:80px;">
        <router-view></router-view>
    </div>

</div>
</template>
<script>
export default {
    data(){
        return{
            employeeData: JSON.parse(localStorage.getItem("employeeLogin")),
        }
    },
    methods:{
        checkLogin() {
            if (localStorage.getItem("employeeLogin") != null)
                this.$router.push("dashboard")
            else
                this.$router.push("login")
        },
        logout(){
            localStorage.clear()
            this.$router.push("login")
        },
    },
    mounted(){
        this.checkLogin()
    }
}
</script>