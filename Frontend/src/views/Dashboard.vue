<template>
<div>
    <b-card-group deck v-if="this.selectMenu == 1">
        <b-card header="" header-tag="header" footer="จัดทำโดย นายฉัตรดนัย ผากาเกตุ B6003234" footer-tag="footer" title="Shipping State System">
            <b-card-text>ระบบจัดการสถานะพัสดุ</b-card-text>
            <b-button to="shippingstate" variant="primary">ไปที่หน้าโปรแกรม</b-button>
        </b-card>

        <b-card header="" header-tag="header" footer="จัดทำโดย ............" footer-tag="footer" title="....... System">
            <b-card-text>ระบบ.........</b-card-text>
            <b-button to="?????" variant="primary">ไปที่หน้าโปรแกรม</b-button>
        </b-card>

    </b-card-group>

    <b-card-group deck v-if="this.selectMenu == 2">
        <b-card header="" header-tag="header" footer="จัดทำโดย ......." footer-tag="footer" title="Shipping State System">
            <b-card-text>ระบบจัดการสถานะพัสดุ</b-card-text>
            <b-button to="shippingstate" variant="primary">ไปที่หน้าโปรแกรม</b-button>
        </b-card>

        <b-card header="" header-tag="header" footer="จัดทำโดย ............" footer-tag="footer" title="....... System">
            <b-card-text>ระบบ.........</b-card-text>
            <b-button to="?????" variant="primary">ไปที่หน้าโปรแกรม</b-button>
        </b-card>

    </b-card-group>

    <b-card-group deck v-if="this.selectMenu == 3">
        <b-card header="" header-tag="header" footer="จัดทำโดย ....." footer-tag="footer" title="Shipping State System">
            <b-card-text>ระบบจัดการสถานะพัสดุ</b-card-text>
            <b-button to="shippingstate" variant="primary">ไปที่หน้าโปรแกรม</b-button>
        </b-card>

        <b-card header="" header-tag="header" footer="จัดทำโดย ............" footer-tag="footer" title="....... System">
            <b-card-text>ระบบ.........</b-card-text>
            <b-button to="?????" variant="primary">ไปที่หน้าโปรแกรม</b-button>
        </b-card>

    </b-card-group>

</div>
</template>

<script>
export default {
    data() {
        return {
            employeeData: JSON.parse(localStorage.getItem("employeeLogin")),
            selectMenu: 1
        }
    },
    methods: {
        checkLogin() {
            if (localStorage.getItem("employeeLogin") != null)
                this.$router.push("dashboard")
            else
                this.$router.push("login")
        },
        init() {
            this.selectMenu = this.employeeData.employeePosition.id
        }
    },
    mounted() {
        this.init(),
        this.checkLogin()
    }
}
</script>
