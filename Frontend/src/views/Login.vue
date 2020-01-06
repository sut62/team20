<template>
<div>
    <div class="login-form">
        <div>
            <h4 class="modal-title">เข้าสู่ระบบ</h4>
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Employee ID" v-model="employeeId" required="required">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Email address" v-model="email" required="required">
            </div>

            <input type="submit" @click="this.submit" class="btn btn-primary btn-block btn-lg " value="Login">
        </div>
    </div>
</div>
</template>

<script>
import api from "../apiConnector"
export default {
    data() {
        return {
            email: null,
            employeeId: null,
        }
    },
    methods: {
        checkLogin() {
            if (localStorage.getItem("employeeLogin") != null)
                this.$router.push("dashboard")
        },
        submit() {
            api.post("/chkLogin", {
                    employeeId: this.employeeId,
                    email: this.email
                })
                .then(response => {
                    if (response.data != "") {
                        localStorage.setItem("employeeLogin", JSON.stringify(response.data))
                        this.$router.push("dashboard")
                    } else {
                        alert("Login failed")
                        this.$router.push("login")
                    }
                })
        },
    },
    mounted() {
        this.checkLogin()
    }
}
</script>

<style>
body {
    font-family: 'Varela Round', sans-serif;
}

.login-form {
    width: 350px;
    margin: 100px auto;
    padding: 30px 0;
}

.login-form {
    color: #434343;
    border-radius: 1px;
    margin-bottom: 15px;
    background: #fff;
    border: 1px solid #f3f3f3;
    box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
    padding: 30px;
}

.login-form h4 {
    text-align: center;
    font-size: 22px;
    margin-bottom: 20px;
}

.login-form .form-group {
    margin-bottom: 20px;
}

.login-form .form-control,
.login-form .btn {
    min-height: 40px;
    border-radius: 2px;
    transition: all 0.5s;
}

.login-form .close {
    position: absolute;
    top: 15px;
    right: 15px;
}

.login-form .btn {
    background: #4aba70;
    border: none;
    line-height: normal;
}

.login-form .btn:hover,
.login-form .btn:focus {
    background: #42ae68;
}

.login-form .checkbox-inline {
    float: left;
}

.login-form input[type="checkbox"] {
    margin-top: 2px;
}

.login-form .forgot-link {
    float: right;
}

.login-form .small {
    font-size: 13px;
}

.login-form a {
    color: #4aba70;
}
</style>
