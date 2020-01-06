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
                    <b-form-input list="input-list" v-model="this.MemberCustomer.mname" id="input-with-list"></b-form-input>

                    <label for="input-with-list">กรอกเบอร์โทรศัพท์</label>
                    <b-form-input list="input-list" v-model="this.MemberCustomer.tel" id="input-with-list"></b-form-input>

                    <label for="selectList">เลือกระดับสมาชิก</label>
                    <b-form-select v-model="this.MemberCustomer.memberLevelId" :options="this.stationData" class="mb-3" value-field="id" text-field="name" disabled-field="notEnabled" id="selectList"></b-form-select>

                    <label for="selectList">เลือกประเภทสมาชิก</label>
                    <b-form-select v-model="this.MemberCustomer.memberTypeId" :options="this.stationData" class="mb-3" value-field="id" text-field="name" disabled-field="notEnabled" id="selectList"></b-form-select>

                    <label for="selectList">เลือกชื่อพนักงาน</label>
                    <b-form-select v-model="this.MemberCustomer.employeeId" :options="this.stationData" class="mb-3" value-field="id" text-field="name" disabled-field="notEnabled" id="selectList"></b-form-select>

                </b-col>
                <b-col cols="1"></b-col>
                <b-col>
                    <b>สถานะการสมัครสมาชิก</b>
                    <div class="text-left mt-2 text-break">
                        <div class="badge badge-danger text-wrap">
                            โปรดระมัดระวังข้อมูล!!! การบันทึกซ่ำซ้อนอาจทำให้เสียค่าใช้จ่ายในการสมัครเพิ่มเติมเพิ่มเติม
                        </div>
                        <div>
                            สถานะ :
                            <span v-if="this.haveSave">
                                <div v-if="this.SaveComplete" class="badge badge-success text-wrap" style="width: 6rem;">
                                    บันทึกข้อมูลสำเร็จ
                                </div>
                                <div v-else class="badge badge-danger text-wrap" style="width: 6rem;">
                                    บันทึกข้อมูลล้มเหลว
                                </div>
                            </span>

                            <span v-if="!this.haveSave">
                                <div class="badge badge-warning text-wrap" style="width: 8rem;">
                                    ยังไม่ได้รับการบันทึก
                                </div>
                            </span>

                        </div>

                        <div v-if="this.SaveComplete">
                            <hr>
                            <div class="ml-2">
                                <p class="text-center mb-1"><b>ข้อมูลสมาชิก</b></p>
                                ชื่อสมาชิก : {{this.MemberCustomer.mname}} <br>
                                เบอร์โทรศัพท์ : {{this.MemberCustomer.tel}} <br>
                                ระดับสมาชิก : {{this.MemberCustomer.memberLevelId}} <br>
                                ประเภทสมาชิก : {{this.MemberCustomer.memberTypeId}} <br>
                                ผู้สมัคร : {{this.MemberCustomer.employeeId}} <br>
                            </div>
                        </div>

                    </div>
                </b-col>
                <b-col cols="1"></b-col>
            </b-row>

            <hr v-if="this.foundPackage">

            <div>
                <hr>
                <b-button variant="primary" @click="this.Save">บันทึก</b-button>

            </div>
        </b-card-body>
    </b-card>
</div>
</template>

<script>
export default {
    data() {
        return {
            SaveComplete: false,
            haveSave: false,
            ShippingState: {
                packageId: null,
                employeeId: null,
                stationId: null,
                statusId: null,
            },
            packageData: {
                srcName: "test",
                dstName: "test2",
                srcStation: "sT",
                dstStation: "dT"
            },
            MemberCustomer: {
                mname: null,
                tel: null,
                memberLevelId: null,
                memberTypeId: null,
                employeeId: null
            },
            employeeData: [{
                    id: 1,
                    name: "test test"
                },
                {
                    id: 2,
                    name: "test2 test2"
                }
            ],
            stationData: [{
                    id: 1,
                    name: "Station test1",
                },
                {
                    id: 2,
                    name: "Station test2",
                },
            ],
            statusData: [{
                    id: 1,
                    name: "Status test1",
                },
                {
                    id: 2,
                    name: "Status test2",
                },
            ]
        }
    },
    methods: {
        Save() {
            this.SaveComplete = true
            this.haveSave = true
            alert("บันทึกสถานะพัสดุสำเร็จ")
        }
    }
}
</script>
