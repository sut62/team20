<template>
<div>
    <b-card no-body>
        <b-card-header header-tag="nav">
            <b-nav card-header tabs>
                <b-nav-item active>ระบบแสดงประวัติการจัดส่งพัสดุ</b-nav-item>
            </b-nav>
        </b-card-header>

        <b-card-body class="text-center">
            <b-card-title>ประวัติการจัดส่ง</b-card-title>
            <div v-if="this.FO">

                <div v-if="this.checkin" class="badge badge-success text-wrap">
                    ยืนยันสถานะสมบูรณ์
                </div>
                <div v-else class="badge badge-danger text-wrap">
                    ยื่นยันระบบล้มเหลว กรุณายืนยันสถานะพนักงาน {{ this.employeeData.name }}
                </div>
            </div>
            <div v-else class="badge badge-warning">
                กรุณายืนยันสถานะพนักงาน {{ this.employeeData.name }}
            </div>

            <div class="form-group">
                <input type="text" class="form-control" placeholder="Email address" v-model="email" required="required">
            </div>
            <b-button pill variant="primary" @click="this.chk">ยืนยันการรับข้อมูล</b-button>

        </b-card-body>
        <div v-if="this.checkin">
            <b-table striped hover :items="this.historySentParcel"></b-table>
        </div>

    </b-card>

</div>
</template>

<script>
import api from "../../apiConnector"
export default {
    data() {
        return {
            employeeData: JSON.parse(localStorage.getItem("employeeLogin")),
            email: null,
            checkin: false,
            historySentParcel: [],
            chking: "",
            failcase: 0,
            FO: false

        }
    },
    methods: {
        chk() {
            if (this.chking == this.email) {
                this.checkin = true;
            } else {

                this.checkin = false;
            }
            this.FO = true;
        },
        getMemberCustomer() {
            api.get("/getAllSentParcel")
                .then(response => {
                    var object = this.sourceIndex(response.data)
                    var dict = []
                    for (var i in object) {
                        var item = {}
                        item["Package"] = object[i].packageCode
                        item["สถานีต้นทาง"] = object[i].atOriginStation.name
                        item["สถานีต่อไป"] = object[i].atArriveStation.name
                        item["ช่วงเวลาที่จัดส่ง"] = object[i].sentparcel.ftime+ "-" + object[i].sentparcel.ltime

                        dict[i] = item
                    }

                    this.historySentParcel = dict
                })
        },
        sourceIndex: function (arr) {
            return arr.slice().sort(function (a, b) {
                return a.id - b.id;
            });
        },
        init() {
            this.chking = this.employeeData.email;
        }
    },

    mounted() {
        this.getMemberCustomer();
        this.init();
    }
}
</script>
