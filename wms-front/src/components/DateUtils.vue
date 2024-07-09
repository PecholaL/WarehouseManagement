<template>
    <div style="">
        <span class="time" id="time">
            今天是: <span class="date">{{ nowTime }}</span>
            <span class="hour" style="">{{ time.hour }}</span>
            <a>:</a>
            <span class="minute" style="">{{ time.minute }}</span>
            <a class="split">:</a>
            <span class="second" style="">{{ time.second }}</span>
        </span>
    </div>
</template>

<script>
export default {
    name: "DateUtils",
    props: ["s"],
    data() {
        return {
            time: {
                hour: "",
                minute: "",
                second: "",
            },
            nowTime: "",
            week: [
                "【日】",
                "【月】",
                "【火】",
                "【水】",
                "【木】",
                "【金】",
                "【土】",
            ]
        };
    },
    mounted() {
        this.dateTime();
    },
    methods: {
        dateTime() {
            this.timeFormate();
            setTimeout(()=>{
                this.dateTime();
            }, 1000)
        },

        timeFormate() {
            const newtime = new Date();
            this.time.hour = this.getIncrease(newtime.getHours(), 2);
            this.time.minute = this.getIncrease(newtime.getMinutes(), 2);
            this.time.second = this.getIncrease(newtime.getSeconds(), 2);
            this.nowTime = 
                this.getIncrease(newtime.getFullYear(), 4) + 
                "年" + 
                this.getIncrease(newtime.getMonth()+1, 2) + 
                "月" + 
                this.getIncrease(newtime.getDate(), 2) + 
                "日" + 
                this.week[newtime.getDay()];
        },
        getIncrease(num, digit) {
            var increase = "";
            for (var i=0; i<digit; i++) {
                increase += "0";
            }
            return (increase + num).slice(-digit);
        }
    }
}
</script>

<style scoped>
    .txt-data .time {
        font-size: 1rem;
        margin-right: 0.5rem;
    }
    .split {
        animation: shark 1s step-end infinite;
        vertical-align: center;
        margin-left: 2px;
        margin-right: 2px;
    }
    @keyframes shark {
        0%,
        100% {
            opacity: 1;
        }
        50% {
            opacity: 0;
        }
    }
</style>