<template>
<div>
    <b-card no-body>
        <b-card-header header-tag="nav">
            <b-nav card-header tabs>
                <b-nav-item active>ระบบสมัครสมาชิกให้ลูกค้า</b-nav-item>
            </b-nav>
        </b-card-header>

        <b-card-body class="text-center">
            <b-card-title>แบบฟอร์มสมัครสมาชิก</b-card-title>

            <hr>

            <b-row class="mt-4 mb-4">
                <b-col cols="1"></b-col>
                <b-col cols="5">

                    <label for="input-with-list">กรอกชื่อ</label>
                    <b-form-input list="input-list" v-model="MemberCustomer.mname" id="input-with-list"></b-form-input>

                    <label for="input-with-list">กรอกเบอร์โทรศัพท์</label>
                    <b-form-input list="input-list" v-model="MemberCustomer.tel" id="input-with-list"></b-form-input>

                    <label for="input-with-list">กรอกEmail(สามารถเว้นว่างได้)</label>
                    <b-form-input list="input-list" v-model="MemberCustomer.email" id="input-with-list"></b-form-input>

                    <label for="selectList">เลือกระดับสมาชิก</label>
                    <b-form-select v-model="MemberCustomer.memberLevelId" :options="this.memberLevel" class="mb-3" value-field="id" text-field="permission" disabled-field="notEnabled" id="selectList"></b-form-select>

                    <label for="selectList">เลือกประเภทสมาชิก</label>
                    <b-form-select v-model="MemberCustomer.memberTypeId" :options="this.memberType" class="mb-3" value-field="id" text-field="type" disabled-field="notEnabled" id="selectList"></b-form-select>

                    <label for="selectList">เลือกชื่อพนักงาน</label>
                    <b-form-select v-model="MemberCustomer.employeeId" :options="this.employeeData" class="mb-3" value-field="id" text-field="name" disabled-field="notEnabled" id="selectList"></b-form-select>

                </b-col>
                <b-col cols="1"></b-col>
                <b-col>
                    <b>สถานะการสมัครสมาชิก</b>
                    <div class="text-left mt-2 text-break">
                        <div class="badge badge-warning text-wrap">
                            โปรดระมัดระวังข้อมูล!!! การบันทึกซ่ำซ้อนอาจทำให้เสียค่าใช้จ่ายในการสมัครเพิ่มเติมเพิ่มเติม
                        </div>
                        <b-alert class="mt-3 mb-4" :show="saveStatus.popup.showAlert" dismissible fade :variant="saveStatus.popup.variant">
                            {{this.saveStatus.popup.message}}
                        </b-alert>
                        <div>
                            <div class="badge badge-light text-wrap" style="width: 15rem;">
                            </div>
                        </div>

                    </div>
                </b-col>
                <b-col cols="1"></b-col>
            </b-row>

            <div>
                <hr>
                <b-button variant="primary" @click="this.Save">บันทึก</b-button>

            </div>
        </b-card-body>
    </b-card>
</div>
</template>

<script>
import api from "../../apiConnector"
export default {
    data() {
        return {
            memberLevel: [],
            memberType: [],
            employeeData: [],
            MemberCustomer: {
                mname: null,
                tel: null,
                email: null,
                memberLevelId: null,
                memberTypeId: null,
                employeeId: null
            },
            saveStatus: {
                popup: {
                    showAlert: false,
                    variant: "danger",
                    message: ""
                }
            }

        }
    },
    methods: {
        Save() {
            api.post("/addMemberCustomer", {
                    mname: this.MemberCustomer.mname,
                    tel: this.MemberCustomer.tel,
                    email: this.MemberCustomer.email,
                    levelId: this.MemberCustomer.memberLevelId,
                    typeId: this.MemberCustomer.memberTypeId,
                    employeeId: this.MemberCustomer.employeeId
                })
                .then(
                    response => {
                        if (response.data) {
                            this.saveStatus.popup.showAlert = true
                            setTimeout(()=> {
                                this.saveStatus.popup.showAlert = false
                            }, 3000);
                            this.saveStatus.popup.variant = "success"
                            this.saveStatus.popup.message = "สมัครสมาชิกสำเร็จ สมาชิกใหม่IDคือ " + response.data.id

                        }
                    },
                    error => {
                        if (error) {
                            this.saveStatus.popup.showAlert = true
                            setTimeout(()=> {
                                this.saveStatus.popup.showAlert = false
                            }, 3000);
                            this.saveStatus.popup.variant = "danger"
                            this.saveStatus.popup.message = "สมัครสมาชิกไม่สำเร็จ"

                        }
                    }
                )
        },
        getMemberLevel() {
            api.get("/MemberLevel")
                .then(response => {
                    this.memberLevel = response.data
                })
        },
        getMemberType() {
            api.get("/MemberType")
                .then(response => {
                    this.memberType = response.data
                })
        },
        getEmployee() {
            api.get("/getEmployees")
                .then(response => {
                    this.employeeData = response.data
                })
        }

    },
    mounted() {
        this.getMemberLevel();
        this.getMemberType();
        this.getEmployee();
    }
}
</script>
