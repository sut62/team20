<template>
<div>
    <b-card no-body>
        <b-card-header header-tag="nav">
            <b-nav card-header tabs>
                <b-nav-item active>ระบบแสดงข้อมูลสมาชิก</b-nav-item>
            </b-nav>
        </b-card-header>

        <b-card-body class="text-center">
            <b-card-title>ข้อมูลสมาชิก</b-card-title>
            <div v-if="this.FO">

                <div v-if="this.checkin" class="badge badge-success text-wrap">
                    ยืนยันสถานะสมบูรณ์
                </div>
                <div v-else class="badge badge-danger text-wrap">
                    ยื่นยันระบบล้มเหลว กรุณายืนยันสถานะพนักงาน {{ this.employeeData.name }}
                </div>
            </div>
            <div v-else class="badge badge-primary">
                กรุณายืนยันสถานะพนักงาน {{ this.employeeData.name }}
            </div>

            <div class="form-group">
                <input type="text" class="form-control" placeholder="Email address" v-model="email" required="required">
            </div>
            <b-button variant="primary" @click="this.chk">ยืนยันการรับข้อมูล</b-button>

        </b-card-body>
        <div v-if="this.checkin">
            <b-table striped hover :items="this.MemberCustomer"></b-table>
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
            MemberCustomer: [],
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
            api.get("/getMemberCustomer")
                .then(response => {
                    var object = this.sourceIndex(response.data)
                    var dict = []
                    for (var i in object) {
                        var item = {}
                        item["id"] = object[i].id
                        item["Member Name	"] = object[i].MemName
                        item["Member Tel	"] = object[i].Tel
                        item["Member Email	"] = object[i].email
                        item["Member Type	"] = object[i].memberType.type
                        item["Member Level	"] = object[i].memberLevel.permission
                        item["CreateBy	"] = object[i].createBy.name
                        dict[i] = item
                    }

                    this.MemberCustomer = dict
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
