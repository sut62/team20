<template>
<div>
    <b-card-group deck v-if="this.selectMenu == 1">
        <b-card header="" header-tag="header" footer="จัดทำโดย นายฉัตรดนัย ผากาเกตุ B6003234" footer-tag="footer" title="Shipping State System">
            <b-card-text>ระบบจัดการสถานะพัสดุ</b-card-text>
            <b-button to="shippingstate" variant="primary">ไปที่หน้าโปรแกรม</b-button>
        </b-card>

        <b-card header="" header-tag="header" footer="จัดทำโดย นายอธิษฐาน จิตรโสภาภรณ์ B6021405" footer-tag="footer" title="Cancelsent System">
            <b-card-text>ระบบยกเลิกพัสดุ</b-card-text>
            <b-button to="cancelsent" variant="primary">ไปที่หน้าโปรแกรม</b-button>
        </b-card>

        <b-card header="" header-tag="header" footer="จัดทำโดย นายฉัตรดนัย ผากาเกตุ B6003234" footer-tag="footer" title="Display Shipping State System">
            <b-card-text>ระบบแสดงสถานะพัสดุ</b-card-text>
            <b-button to="displayshippingstate" variant="primary">ไปที่หน้าโปรแกรม</b-button>
        </b-card>

    </b-card-group>

    <b-card-group deck v-if="this.selectMenu == 2">
        <b-card header="" header-tag="header" footer="จัดทำโดย นายภัทรสิทธิ์ ลอมไธสง B6004798" footer-tag="footer" title="Sent Parcel System">
            <b-card-text>ระบบการจัดส่งพัสดุ</b-card-text>
            <b-button to="sentparcel" variant="primary">ไปที่หน้าโปรแกรม</b-button>
        </b-card>

        <b-card header="" header-tag="header" footer="จัดทำโดย นายกิตติธร แสงจีน B6012496" footer-tag="footer" title="Confirm Package System">
            <b-card-text>ระบบยืนยันการรับพัสดุ</b-card-text>
            <b-button to="confirmpackage" variant="primary">ไปที่หน้าโปรแกรม</b-button>
        </b-card>
        
        <b-card header="" header-tag="header" footer="จัดทำโดย นายภัทรสิทธิ์ ลอมไธสง B6004798" footer-tag="footer" title="History Sent Parcel System">
            <b-card-text>ระบบแสดงประวัติการจัดส่งพัสดุ</b-card-text>
            <b-button to="historysentparcel" variant="primary">ไปที่หน้าโปรแกรม</b-button>
        </b-card>

    </b-card-group>

    <b-card-group deck v-if="this.selectMenu == 3">
        <b-card header="" header-tag="header" footer="จัดทำโดย นายไพรัช ต้นเขียน B6009649" footer-tag="footer" title="Packaging System">
            <b-card-text>ระบบรับฝากพัสดุ</b-card-text>
            <b-button to="packaging" variant="primary">ไปที่หน้าโปรแกรม</b-button>
        </b-card>

        <b-card header="" header-tag="header" footer="จัดทำโดย นายชีวิน ศิริพันธุ์ B6011642" footer-tag="footer" title="MemberCustomer System">
            <b-card-text>ระบบสมัครสมาชิกให้ลูกค้า</b-card-text>
            <b-button to="membercustomer" variant="primary">ไปที่หน้าโปรแกรม</b-button>
        </b-card>

        <b-card header="" header-tag="header" footer="จัดทำโดย นายชีวิน ศิริพันธุ์ B6011642" footer-tag="footer" title="Display MemberCustomer System">
            <b-card-text>ระบบแสดงข้อมูลสมาชิก</b-card-text>
            <b-button to="displaymembercustomer" variant="primary">ไปที่หน้าโปรแกรม</b-button>
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
        init() {
            this.selectMenu = this.employeeData.employeePosition.id
        }
    },
    mounted() {
        this.init()
    }
}
</script>
