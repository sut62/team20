<template>
<div>
    <b-card no-body>
        <b-card-header header-tag="nav">
            <b-nav card-header tabs>
                <b-nav-item active>ระบบแสดงค่าใช้จ่ายการรับฝากพัสดุ</b-nav-item>
            </b-nav>
        </b-card-header>

        <b-card-body class="text-center">
            <b-card-title>แสดงค่าใช้จ่ายการรับฝากพัสดุ</b-card-title>

            <hr>

            <b-row class="mt-4 mb-4">
                <b-col cols="2"></b-col>
                <b-col cols="8">
                    
                    <p class="text-center mb-1"><b>ข้อมูล Package</b></p>
                                รหัสพัสดุ : {{this.packageData.packageCode}} <br>
                                ชื่อผู้ส่ง : {{this.packageData.sentBy}} <br>
                                ต้นสถานี : {{this.packageData.atStation}} <br>
                                ชื่อผู้รับ : {{this.packageData.receiver}} <br>                            
                                ปลายทางสถานี : {{this.packageData.place}} <br>
                                พนักงานที่รับฝาก : {{this.packageData.createBy}} <br>
                                ประเภทการส่ง : {{this.packageData.sendingType}} <br>
                                น้ำหนักสินค้า : {{this.packageData.weight}} <br>
                                ค่าจัดส่ง : {{this.packageData.price}} บาท <br><br>
                    <b-button variant="primary" style="width:50%" @click="this.return">กลับสู่เมนู</b-button>
                </b-col>

                <b-col cols="2"></b-col>
            </b-row>
            <b-alert class="mt-3 mb-4" :show="saveStatus.popup.dismissCountDown" dismissible fade :variant="saveStatus.popup.variant">
                {{this.saveStatus.popup.message}}
            </b-alert>
        </b-card-body>
    </b-card>
</div>
</template>

<script>
import api from "../../apiConnector"
export default {
    data() {
        return {
            code: JSON.parse(localStorage.getItem("PackageCode")),
            packageData: {
                packageCode: "",
                sentBy: "",
                createBy: "",
                atStation: "",
                place: "",
                receiver: "",
                sendingType: "",
                weight: "",
                price: ""
            },
            saveStatus: {
                popup: {
                    dismissSecs: 10,
                    dismissCountDown: 0,
                    showDismissibleAlert: false,
                    variant: "danger",
                    message: ""
                }
            },
        }
    },
    methods: {
        return(){
            this.$router.push("dashboard")
        },
        getData(){
            api.get("/getPackagePayment/"+this.code)
            .then(
                response => {
                    this.packageData.packageCode = this.code
                    this.packageData.sentBy = response.data.sentBy
                    this.packageData.createBy = response.data.createBy
                    this.packageData.atStation = response.data.atStation
                    this.packageData.place = response.data.place
                    this.packageData.receiver = response.data.receiver
                    this.packageData.sendingType = response.data.sendingType
                    this.packageData.weight = response.data.weight
                    this.packageData.price = response.data.price
                },
                error => {
                    if(error){
                        this.saveStatus.popup.dismissCountDown = this.saveStatus.popup.dismissSecs
                        this.saveStatus.popup.variant = "danger"
                        this.saveStatus.popup.message = "ไม่พบ Code ของพัสดุ"
                    }
                }
            )
        }
    },
    mounted() {
        this.getData()
    }
}
</script>
