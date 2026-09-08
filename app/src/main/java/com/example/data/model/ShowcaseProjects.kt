package com.example.data.model

data class WebProject(
    val id: String,
    val prefix: String,
    val titleFa: String,
    val categoryFa: String,
    val descriptionFa: String,
    val iconName: String,
    val htmlCode: String
)

object ShowcaseProjects {

    val projects: List<WebProject> = listOf(
        WebProject(
            id = "proj_music_player",
            prefix = "hcj56",
            titleFa = "اپلیکیشن موزیک پلیر مدرن (Music Player)",
            categoryFa = "چندرسانه‌ای و صوت",
            descriptionFa = "پخش‌کننده صوتی کامل با لیست پخش، دکمه‌های کنترل ترانه، نوار پیشرفت صوتی، کنترل بلندی صدا و تصویر پس‌زمینه محو شده.",
            iconName = "music",
            htmlCode = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>موزیک پلیر تیروتیر</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; font-family: Tahoma, sans-serif; }
        body { background: #0f172a; min-height: 100vh; display: flex; justify-content: center; align-items: center; padding: 20px; color: white; }
        .player-card {
            width: 320px;
            background: linear-gradient(180deg, #1e293b, #0f172a);
            border-radius: 20px;
            padding: 25px;
            box-shadow: 0 20px 40px rgba(0,0,0,0.6);
            border: 1px solid rgba(255,255,255,0.1);
            text-align: center;
        }
        .album-art {
            width: 180px; height: 180px; margin: 0 auto 20px;
            background: linear-gradient(135deg, #06b6d4, #8b5cf6);
            border-radius: 50%;
            display: flex; align-items: center; justify-content: center;
            font-size: 50px;
            box-shadow: 0 10px 25px rgba(6,182,212,0.4);
            animation: rotateDisk 12s linear infinite;
            animation-play-state: paused;
        }
        .album-art.playing { animation-play-state: running; }
        @keyframes rotateDisk { from { transform: rotate(0deg); } to { transform: rotate(360deg); } }
        .song-title { font-size: 18px; font-weight: bold; margin-bottom: 5px; color: #f8fafc; }
        .song-artist { font-size: 14px; color: #94a3b8; margin-bottom: 20px; }
        .slider-box { margin: 15px 0; }
        .progress-bar { width: 100%; height: 6px; -webkit-appearance: none; background: #334155; border-radius: 4px; outline: none; cursor: pointer; }
        .progress-bar::-webkit-slider-thumb { -webkit-appearance: none; width: 14px; height: 14px; border-radius: 50%; background: #38bdf8; }
        .time-box { display: flex; justify-content: space-between; font-size: 12px; color: #94a3b8; margin-top: 4px; }
        .controls { display: flex; justify-content: center; align-items: center; gap: 15px; margin-top: 15px; }
        .ctrl-btn { background: #334155; border: none; color: white; width: 44px; height: 44px; border-radius: 50%; cursor: pointer; font-size: 18px; transition: 0.2s; display: flex; align-items: center; justify-content: center; }
        .ctrl-btn.play { background: #38bdf8; color: #0f172a; width: 56px; height: 56px; font-size: 24px; box-shadow: 0 4px 15px rgba(56,189,248,0.4); }
        .ctrl-btn:hover { transform: scale(1.08); }
        .playlist { margin-top: 20px; text-align: right; background: rgba(255,255,255,0.05); padding: 10px; border-radius: 10px; font-size: 13px; }
        .playlist-item { padding: 6px 8px; border-radius: 4px; cursor: pointer; }
        .playlist-item.active { background: #38bdf8; color: #0f172a; font-weight: bold; }
    </style>
</head>
<body>
    <div class="player-card">
        <div class="album-art" id="disk">🎵</div>
        <div class="song-title" id="songTitle">آهنگ آموزشی شماره ۱</div>
        <div class="song-artist" id="songArtist">آکادمی تیروتیر</div>
        <div class="slider-box">
            <input type="range" class="progress-bar" id="seekBar" value="0" max="100">
            <div class="time-box">
                <span id="currTime">00:00</span>
                <span id="durTime">03:30</span>
            </div>
        </div>
        <div class="controls">
            <button class="ctrl-btn" onclick="prevSong()">⏮</button>
            <button class="ctrl-btn play" id="playBtn" onclick="togglePlay()">▶</button>
            <button class="ctrl-btn" onclick="nextSong()">⏭</button>
        </div>
        <div class="playlist">
            <div class="playlist-item active" onclick="selectSong(0)">۱. پادکست فرانت‌اند تیروتیر</div>
            <div class="playlist-item" onclick="selectSong(1)">۲. معرفی اکوسیستم جاوااسکریپت</div>
            <div class="playlist-item" onclick="selectSong(2)">۳. موسیقی متمرکز برای کدنویسی</div>
        </div>
    </div>
    <script>
        const songs = [
            { title: "پادکست فرانت‌اند تیروتیر", artist: "مهندس تیروتیر", icon: "🎧" },
            { title: "معرفی اکوسیستم جاوااسکریپت", artist: "استاد آنزان", icon: "⚡" },
            { title: "موسیقی متمرکز برای کدنویسی", artist: "Lo-Fi Beats", icon: "💻" }
        ];
        let currentIndex = 0;
        let isPlaying = false;
        let timer = null;
        let currentSeconds = 0;

        function togglePlay() {
            isPlaying = !isPlaying;
            document.getElementById("playBtn").innerText = isPlaying ? "⏸" : "▶";
            document.getElementById("disk").classList.toggle("playing", isPlaying);
            if (isPlaying) {
                timer = setInterval(() => {
                    currentSeconds++;
                    if (currentSeconds > 210) currentSeconds = 0;
                    document.getElementById("seekBar").value = (currentSeconds / 210) * 100;
                    let mins = Math.floor(currentSeconds / 60).toString().padStart(2, '0');
                    let secs = (currentSeconds % 60).toString().padStart(2, '0');
                    document.getElementById("currTime").innerText = mins + ":" + secs;
                }, 1000);
            } else {
                clearInterval(timer);
            }
        }
        function selectSong(index) {
            currentIndex = index;
            document.getElementById("songTitle").innerText = songs[index].title;
            document.getElementById("songArtist").innerText = songs[index].artist;
            document.getElementById("disk").innerText = songs[index].icon;
            document.querySelectorAll(".playlist-item").forEach((el, i) => {
                el.classList.toggle("active", i === index);
            });
            currentSeconds = 0;
            document.getElementById("seekBar").value = 0;
            document.getElementById("currTime").innerText = "00:00";
        }
        function nextSong() { selectSong((currentIndex + 1) % songs.length); }
        function prevSong() { selectSong((currentIndex - 1 + songs.length) % songs.length); }
    </script>
</body>
</html>"""
        ),
        WebProject(
            id = "proj_calculator",
            prefix = "hcj71",
            titleFa = "ماشین‌حساب پیشرفته و مهندسی (Calculator)",
            categoryFa = "محاسباتی و ابزار",
            descriptionFa = "ماشین‌حساب زیبا با پشتیبانی از سوئیچ حالت شب/روز (Dark Mode)، دکمه پاکسازی، حذف آخرین کاراکتر و انیمیشن لمسی کلیدها.",
            iconName = "calc",
            htmlCode = """<!DOCTYPE html>
<html lang="fa" dir="ltr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Advanced Calculator - tirOtir</title>
    <style>
        * { box-sizing: border-box; font-family: 'Segoe UI', Tahoma, sans-serif; transition: background 0.3s, color 0.3s; }
        body { margin: 0; min-height: 100vh; display: flex; justify-content: center; align-items: center; background: #0f172a; padding: 20px; }
        body.light-mode { background: #f1f5f9; }
        .calc-wrapper {
            background: #1e293b;
            padding: 20px;
            border-radius: 20px;
            box-shadow: 0 15px 35px rgba(0,0,0,0.5);
            width: 320px;
            border: 1px solid rgba(255,255,255,0.08);
        }
        body.light-mode .calc-wrapper { background: #ffffff; box-shadow: 0 15px 35px rgba(0,0,0,0.1); }
        .top-bar { display: flex; justify-content: space-between; align-items: center; margin-bottom: 15px; }
        .brand-title { color: #38bdf8; font-size: 14px; font-weight: bold; }
        .theme-btn { background: #334155; color: #f8fafc; border: none; padding: 6px 12px; border-radius: 12px; cursor: pointer; font-size: 12px; }
        body.light-mode .theme-btn { background: #e2e8f0; color: #1e293b; }
        .screen {
            width: 100%;
            height: 70px;
            background: #0f172a;
            border-radius: 12px;
            border: none;
            color: #38bdf8;
            font-size: 32px;
            text-align: right;
            padding: 15px;
            margin-bottom: 20px;
            outline: none;
        }
        body.light-mode .screen { background: #f8fafc; color: #0284c7; border: 1px solid #cbd5e1; }
        .keypad { display: grid; grid-template-columns: repeat(4, 1fr); gap: 10px; }
        button.btn {
            height: 55px;
            border: none;
            border-radius: 12px;
            font-size: 20px;
            font-weight: 600;
            background: #334155;
            color: white;
            cursor: pointer;
            transition: transform 0.1s, filter 0.2s;
        }
        body.light-mode button.btn { background: #e2e8f0; color: #0f172a; }
        button.btn:active { transform: scale(0.95); }
        button.btn.op { background: #0284c7; color: white; }
        button.btn.equal { background: #10b981; color: white; grid-column: span 2; }
        button.btn.clear { background: #ef4444; color: white; }
    </style>
</head>
<body>
    <div class="calc-wrapper">
        <div class="top-bar">
            <span class="brand-title">tirOtir Calc</span>
            <button class="theme-btn" onclick="toggleTheme()">☀️ / 🌙</button>
        </div>
        <input type="text" class="screen" id="disp" value="0" readonly>
        <div class="keypad">
            <button class="btn clear" onclick="clearDisp()">C</button>
            <button class="btn op" onclick="delChar()">DEL</button>
            <button class="btn op" onclick="addChar('/')">/</button>
            <button class="btn op" onclick="addChar('*')">×</button>

            <button class="btn" onclick="addChar('7')">7</button>
            <button class="btn" onclick="addChar('8')">8</button>
            <button class="btn" onclick="addChar('9')">9</button>
            <button class="btn op" onclick="addChar('-')">-</button>

            <button class="btn" onclick="addChar('4')">4</button>
            <button class="btn" onclick="addChar('5')">5</button>
            <button class="btn" onclick="addChar('6')">6</button>
            <button class="btn op" onclick="addChar('+')">+</button>

            <button class="btn" onclick="addChar('1')">1</button>
            <button class="btn" onclick="addChar('2')">2</button>
            <button class="btn" onclick="addChar('3')">3</button>
            <button class="btn" onclick="addChar('.')">.</button>

            <button class="btn" onclick="addChar('0')">0</button>
            <button class="btn equal" onclick="evalExpression()">=</button>
        </div>
    </div>
    <script>
        let screen = document.getElementById("disp");
        function clearDisp() { screen.value = "0"; }
        function delChar() {
            screen.value = screen.value.length > 1 ? screen.value.slice(0, -1) : "0";
        }
        function addChar(c) {
            if (screen.value === "0" && !"+-*/.".includes(c)) screen.value = "";
            screen.value += c;
        }
        function evalExpression() {
            try {
                screen.value = eval(screen.value.replace(/×/g, "*"));
            } catch(e) {
                screen.value = "Error";
            }
        }
        function toggleTheme() {
            document.body.classList.toggle("light-mode");
        }
    </script>
</body>
</html>"""
        ),
        WebProject(
            id = "proj_service_cards",
            prefix = "hcj72",
            titleFa = "کارت‌های خدمات سه‌بعدی با انیمیشن (3D Tilt Cards)",
            categoryFa = "رابط کاربری و CSS",
            descriptionFa = "کارت‌های مدرن خدمات طراحی وب، گرافیک و بهینه‌سازی سئو با افکت شناور و ترانسفورم سه‌بعدی همراه با تم تاریک/روشن.",
            iconName = "cards",
            htmlCode = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>کارت‌های خدمات تیروتیر</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; font-family: Tahoma, sans-serif; }
        body { background: #1e1e24; min-height: 100vh; padding: 30px 15px; color: white; display: flex; flex-direction: column; align-items: center; }
        h1 { margin-bottom: 25px; text-align: center; color: #38bdf8; font-size: 22px; }
        .cards-container { display: flex; flex-wrap: wrap; justify-content: center; gap: 20px; max-width: 900px; }
        .service-card {
            background: #2b2c34;
            border-radius: 16px;
            width: 260px;
            padding: 30px 20px;
            text-align: center;
            border: 1px solid rgba(255,255,255,0.06);
            box-shadow: 0 10px 30px rgba(0,0,0,0.3);
            transition: transform 0.4s ease, box-shadow 0.4s ease;
        }
        .service-card:hover {
            transform: translateY(-12px);
            box-shadow: 0 20px 40px rgba(0,0,0,0.5);
        }
        .card-icon {
            width: 70px; height: 70px; margin: 0 auto 15px;
            border-radius: 50%;
            display: flex; align-items: center; justify-content: center;
            font-size: 32px;
        }
        .card-1 .card-icon { background: rgba(56, 189, 248, 0.15); color: #38bdf8; }
        .card-2 .card-icon { background: rgba(236, 72, 153, 0.15); color: #ec4899; }
        .card-3 .card-icon { background: rgba(34, 197, 94, 0.15); color: #22c55e; }
        .card-title { font-size: 18px; margin-bottom: 10px; font-weight: bold; }
        .card-desc { font-size: 13px; color: #94a3b8; line-height: 1.6; margin-bottom: 20px; }
        .card-btn {
            display: inline-block; padding: 8px 18px; border-radius: 20px; font-size: 13px; text-decoration: none; color: white; font-weight: bold;
        }
        .card-1 .card-btn { background: #0284c7; }
        .card-2 .card-btn { background: #db2777; }
        .card-3 .card-btn { background: #16a34a; }
    </style>
</head>
<body>
    <h1>دپارتمان‌های آموزشی آموزشگاه تیروتیر</h1>
    <div class="cards-container">
        <div class="service-card card-1">
            <div class="card-icon">💻</div>
            <div class="card-title">طراحی و توسعه وب</div>
            <div class="card-desc">آموزش فرانت‌اند، جاوااسکریپت و ری‌اکت مطابق جدیدترین متدهای بین‌المللی</div>
            <a href="#" class="card-btn">ثبت نام در دوره</a>
        </div>
        <div class="service-card card-2">
            <div class="card-icon">🎨</div>
            <div class="card-title">طراحی UI/UX</div>
            <div class="card-desc">اصول تجربه کاربری، فیگما و طراحی سیستم‌های دیزاین واکنش‌گرا و دسترس‌پذیر</div>
            <a href="#" class="card-btn">مشاهده سرفصل</a>
        </div>
        <div class="service-card card-3">
            <div class="card-icon">🚀</div>
            <div class="card-title">هوش مصنوعی و پایتون</div>
            <div class="card-desc">دوره‌های تخصصی پردازش داده، یادگیری ماشین و اتوماسیون آکادمی تیروتیر</div>
            <a href="#" class="card-btn">شروع یادگیری</a>
        </div>
    </div>
</body>
</html>"""
        ),
        WebProject(
            id = "proj_color_picker",
            prefix = "hcj73",
            titleFa = "پالت‌ساز و انتخاب‌گر رنگ پویا (Color Picker)",
            categoryFa = "ابزار توسعه",
            descriptionFa = "تولید رنگ‌های سفارشی با پیش‌نمایش درجا، نمایش کدهای HEX و RGB و دکمه کپی کد برای CSS.",
            iconName = "palette",
            htmlCode = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>انتخاب‌گر رنگ تیروتیر</title>
    <style>
        * { box-sizing: border-box; font-family: Tahoma, sans-serif; }
        body { background: #f1f5f9; min-height: 100vh; display: flex; flex-direction: column; align-items: center; justify-content: center; padding: 20px; }
        .picker-box { background: white; padding: 30px; border-radius: 16px; box-shadow: 0 10px 25px rgba(0,0,0,0.08); text-align: center; width: 300px; }
        .preview-circle { width: 130px; height: 130px; margin: 0 auto 20px; border-radius: 50%; border: 4px solid white; box-shadow: 0 8px 16px rgba(0,0,0,0.15); transition: background 0.2s; }
        input[type="color"] { border: none; width: 60px; height: 60px; border-radius: 50%; cursor: pointer; margin-bottom: 15px; outline: none; background: transparent; }
        .code-display { font-family: monospace; font-size: 16px; background: #f8fafc; padding: 10px; border-radius: 8px; margin: 6px 0; border: 1px solid #e2e8f0; font-weight: bold; }
        .btn-copy { background: #2563eb; color: white; border: none; padding: 8px 16px; border-radius: 6px; cursor: pointer; font-size: 13px; margin-top: 10px; }
    </style>
</head>
<body>
    <div class="picker-box">
        <h3>انتخاب‌گر رنگ فرانت‌اند</h3>
        <div class="preview-circle" id="circle" style="background: #3b82f6;"></div>
        <input type="color" id="picker" value="#3b82f6" oninput="updateColor(this.value)">
        <div class="code-display" id="hexText">HEX: #3B82F6</div>
        <div class="code-display" id="rgbText">RGB: 59, 130, 246</div>
        <button class="btn-copy" onclick="copyCss()">کپی دستور CSS</button>
    </div>
    <script>
        function updateColor(val) {
            document.getElementById("circle").style.background = val;
            document.getElementById("hexText").innerText = "HEX: " + val.toUpperCase();
            let r = parseInt(val.substr(1,2), 16);
            let g = parseInt(val.substr(3,2), 16);
            let b = parseInt(val.substr(5,2), 16);
            document.getElementById("rgbText").innerText = "RGB: " + r + ", " + g + ", " + b;
        }
        function copyCss() {
            const hex = document.getElementById("picker").value;
            navigator.clipboard.writeText("background-color: " + hex + ";");
            alert("کد CSS کپی شد: background-color: " + hex);
        }
    </script>
</body>
</html>"""
        ),
        WebProject(
            id = "proj_countdown_timer",
            prefix = "hcj74",
            titleFa = "شمارش معکوس زنده و زمان‌سنج رویدادها (Countdown Timer)",
            categoryFa = "زمان و تقویم",
            descriptionFa = "تایمر آنلاین با محاسبه میلی‌ثانیه‌ای روز، ساعت، دقیقه و ثانیه تا موعد تحویل پروژه یا آزمون دوره.",
            iconName = "timer",
            htmlCode = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>شمارش معکوس رویداد</title>
    <style>
        * { box-sizing: border-box; font-family: Tahoma, sans-serif; }
        body { background: #0f172a; color: white; min-height: 100vh; display: flex; flex-direction: column; align-items: center; justify-content: center; padding: 20px; text-align: center; }
        h1 { color: #38bdf8; font-size: 24px; margin-bottom: 10px; }
        .countdown-grid { display: flex; gap: 15px; margin: 25px 0; }
        .time-card { background: #1e293b; padding: 20px 15px; border-radius: 14px; min-width: 65px; border: 1px solid rgba(255,255,255,0.08); }
        .time-value { font-size: 32px; font-weight: bold; color: #f43f5e; font-family: monospace; }
        .time-label { font-size: 12px; color: #94a3b8; margin-top: 5px; }
        .event-info { background: rgba(56, 189, 248, 0.1); padding: 12px 20px; border-radius: 8px; font-size: 14px; }
    </style>
</head>
<body>
    <h1>روزشمار آزمون جامع فنی‌حرفه‌ای تیروتیر</h1>
    <div class="countdown-grid">
        <div class="time-card"><div class="time-value" id="days">00</div><div class="time-label">روز</div></div>
        <div class="time-card"><div class="time-value" id="hours">00</div><div class="time-label">ساعت</div></div>
        <div class="time-card"><div class="time-value" id="mins">00</div><div class="time-label">دقیقه</div></div>
        <div class="time-card"><div class="time-value" id="secs">00</div><div class="time-label">ثانیه</div></div>
    </div>
    <div class="event-info">📅 تاریخ رویداد: پایان ترم جاری و تحویل پروژه‌های وب</div>
    <script>
        const targetDate = new Date().getTime() + (14 * 24 * 60 * 60 * 1000) + (5 * 60 * 60 * 1000);
        setInterval(() => {
            const now = new Date().getTime();
            const diff = targetDate - now;
            if (diff > 0) {
                const d = Math.floor(diff / (1000 * 60 * 60 * 24));
                const h = Math.floor((diff % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60));
                const m = Math.floor((diff % (1000 * 60 * 60)) / (1000 * 60));
                const s = Math.floor((diff % (1000 * 60)) / 1000);
                document.getElementById("days").innerText = d.toString().padStart(2, '0');
                document.getElementById("hours").innerText = h.toString().padStart(2, '0');
                document.getElementById("mins").innerText = m.toString().padStart(2, '0');
                document.getElementById("secs").innerText = s.toString().padStart(2, '0');
            }
        }, 1000);
    </script>
</body>
</html>"""
        ),
        WebProject(
            id = "proj_dynamic_greeting",
            prefix = "hcj76",
            titleFa = "خوش‌آمدگویی هوشمند زمان‌محور (Dynamic Greeting)",
            categoryFa = "رابط کاربری و تعامل",
            descriptionFa = "تشخیص ساعت روز کاربر و تغییر پس‌زمینه و پیام به 'صبح بخیر'، 'عصر بخیر' یا 'شب بخیر'.",
            iconName = "sun",
            htmlCode = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>خوش‌آمدگویی هوشمند تیروتیر</title>
    <style>
        * { box-sizing: border-box; font-family: Tahoma, sans-serif; }
        body { min-height: 100vh; margin: 0; display: flex; flex-direction: column; align-items: center; justify-content: center; padding: 20px; transition: background 0.8s ease; color: white; text-align: center; }
        .greeting-card { background: rgba(0,0,0,0.3); backdrop-filter: blur(10px); padding: 35px 25px; border-radius: 20px; border: 1px solid rgba(255,255,255,0.2); max-width: 400px; }
        .greet-title { font-size: 26px; font-weight: bold; margin-bottom: 10px; }
        .clock { font-size: 40px; font-family: monospace; font-weight: bold; margin: 15px 0; }
        .tip { font-size: 14px; opacity: 0.9; }
    </style>
</head>
<body>
    <div class="greeting-card">
        <div class="greet-title" id="greetingText">در حال بررسی ساعت...</div>
        <div class="clock" id="clockText">00:00:00</div>
        <div class="tip" id="tipText">آماده برای یادگیری یک مهارت نوین فرانت‌اند هستید؟</div>
    </div>
    <script>
        function updateGreeting() {
            const now = new Date();
            const h = now.getHours();
            const timeStr = now.toLocaleTimeString('fa-IR');
            document.getElementById("clockText").innerText = timeStr;

            let greet = "";
            let bg = "";
            if (h >= 5 && h < 12) {
                greet = "🌅 صبح بهاری‌تان بخیر، کدبانوی آینده!";
                bg = "linear-gradient(135deg, #0284c7, #38bdf8)";
            } else if (h >= 12 && h < 18) {
                greet = "☀️ بعد از ظهر پر انرژی شما بخیر!";
                bg = "linear-gradient(135deg, #f59e0b, #d97706)";
            } else {
                greet = "🌙 شامگاه بخیر، شب‌زنده‌داران دنیای برنامه‌نویسی!";
                bg = "linear-gradient(135deg, #1e1b4b, #312e81)";
            }
            document.getElementById("greetingText").innerText = greet;
            document.body.style.background = bg;
        }
        setInterval(updateGreeting, 1000);
        updateGreeting();
    </script>
</body>
</html>"""
        ),
        WebProject(
            id = "proj_responsive_navbar",
            prefix = "hcj68",
            titleFa = "ناوبری ریسپانسیو با مگامنو (Responsive Navbar)",
            categoryFa = "رابط کاربری و CSS",
            descriptionFa = "منوی ناوبری حرفه‌ای با زیرمنوهای بازشونده، دکمه همبرگری موبایل و پویانمایی لغزشی نرم.",
            iconName = "menu",
            htmlCode = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>منوی ناوبری تیروتیر</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; font-family: Tahoma, sans-serif; }
        body { background: #f8fafc; }
        nav { background: #1e293b; padding: 12px 24px; display: flex; justify-content: space-between; align-items: center; color: white; position: relative; }
        .logo { font-size: 18px; font-weight: bold; color: #38bdf8; display: flex; align-items: center; gap: 8px; }
        .nav-links { list-style: none; display: flex; gap: 20px; }
        .nav-links li { position: relative; }
        .nav-links a { color: #f1f5f9; text-decoration: none; font-size: 14px; padding: 8px 12px; display: block; border-radius: 6px; }
        .nav-links a:hover { background: #334155; color: #38bdf8; }
        .dropdown-menu { display: none; position: absolute; top: 100%; right: 0; background: #334155; min-width: 170px; border-radius: 8px; list-style: none; padding: 8px 0; box-shadow: 0 10px 20px rgba(0,0,0,0.3); z-index: 10; }
        .nav-links li:hover .dropdown-menu { display: block; }
        .menu-toggle { display: none; font-size: 24px; cursor: pointer; background: none; border: none; color: white; }
        @media (max-width: 768px) {
            .menu-toggle { display: block; }
            .nav-links { display: none; flex-direction: column; position: absolute; top: 100%; left: 0; width: 100%; background: #1e293b; padding: 15px; gap: 10px; }
            .nav-links.active { display: flex; }
            .dropdown-menu { position: static; box-shadow: none; padding-right: 15px; }
        }
        .hero { padding: 40px 20px; text-align: center; }
    </style>
</head>
<body>
    <nav>
        <div class="logo">⚡ تیروتیر وب</div>
        <button class="menu-toggle" onclick="toggleNav()">☰</button>
        <ul class="nav-links" id="navLinks">
            <li><a href="#">صفحه نخست</a></li>
            <li>
                <a href="#">دوره‌های آموزشی ▾</a>
                <ul class="dropdown-menu">
                    <li><a href="#">فرانت‌اند HTML/CSS</a></li>
                    <li><a href="#">جاوااسکریپت پیشرفته</a></li>
                    <li><a href="#">هوش مصنوعی تیروتیر</a></li>
                </ul>
            </li>
            <li><a href="#">درباره آموزشگاه</a></li>
            <li><a href="#">ثبت نام و شهریه</a></li>
        </ul>
    </nav>
    <div class="hero">
        <h2>سیستم منوی استاندارد سازگار با انواع ابعاد نمایشگر</h2>
        <p style="color: #64748b; margin-top: 10px;">در صفحات کوچک دکمه همبرگری فعال شده و منو به صورت آکاردئونی ظاهر می‌شود.</p>
    </div>
    <script>
        function toggleNav() {
            document.getElementById("navLinks").classList.toggle("active");
        }
    </script>
</body>
</html>"""
        ),
        WebProject(
            id = "proj_sidebar_menu",
            prefix = "hcj46",
            titleFa = "نوار کناری متحرک با آیکون‌های تعاملی (Animated Sidebar)",
            categoryFa = "رابط کاربری و CSS",
            descriptionFa = "سایدبار ناوبری با آیکون‌های متحرک، حالت جمع‌شونده و افکت نئونی هاور بر روی آیتم‌های انتخابی.",
            iconName = "sidebar",
            htmlCode = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Animated Sidebar</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; font-family: Tahoma, sans-serif; }
        body { background: #0f172a; min-height: 100vh; display: flex; }
        .sidebar {
            width: 70px;
            background: #1e293b;
            min-height: 100vh;
            transition: width 0.4s;
            overflow: hidden;
            border-left: 1px solid rgba(255,255,255,0.08);
            position: relative;
        }
        .sidebar.open { width: 220px; }
        .toggle-btn {
            height: 60px; display: flex; align-items: center; justify-content: center;
            font-size: 22px; color: #38bdf8; cursor: pointer; border-bottom: 1px solid rgba(255,255,255,0.06);
        }
        .nav-list { list-style: none; margin-top: 15px; }
        .nav-item {
            display: flex; align-items: center; padding: 14px 20px; color: #94a3b8; cursor: pointer; text-decoration: none; white-space: nowrap; transition: 0.2s;
        }
        .nav-item:hover, .nav-item.active { background: rgba(56, 189, 248, 0.1); color: #38bdf8; border-right: 4px solid #38bdf8; }
        .nav-icon { font-size: 20px; min-width: 30px; }
        .nav-text { margin-right: 15px; font-size: 14px; font-weight: 500; }
        .main-content { flex: 1; padding: 30px; color: white; }
    </style>
</head>
<body>
    <div class="sidebar" id="sb">
        <div class="toggle-btn" onclick="toggleSidebar()">☰</div>
        <ul class="nav-list">
            <li class="nav-item active"><span class="nav-icon">🏠</span><span class="nav-text">داشبورد کارآموز</span></li>
            <li class="nav-item"><span class="nav-icon">📚</span><span class="nav-text">سرفصل دروس</span></li>
            <li class="nav-item"><span class="nav-icon">💡</span><span class="nav-text">کد چالش‌ها</span></li>
            <li class="nav-item"><span class="nav-icon">🏆</span><span class="nav-text">گواهی‌نامه تیروتیر</span></li>
            <li class="nav-item"><span class="nav-icon">⚙️</span><span class="nav-text">تنظیمات</span></li>
        </ul>
    </div>
    <div class="main-content">
        <h2>سامانه آموزشی تیروتیر وب</h2>
        <p style="color: #94a3b8; margin-top: 8px;">روی دکمه همبرگری بالای منو کلیک کنید تا منو باز و بسته شود.</p>
    </div>
    <script>
        function toggleSidebar() {
            document.getElementById("sb").classList.toggle("open");
        }
    </script>
</body>
</html>"""
        ),
        WebProject(
            id = "proj_todo_list",
            prefix = "hcj47",
            titleFa = "مدیریت وظایف و برنامه‌ریزی درسی (To-Do List)",
            categoryFa = "برنامه‌های کاربردی",
            descriptionFa = "افزودن تسک‌ها، خط زدن موارد انجام شده، حذف و ذخیره‌سازی وضعیت با LocalStorage مرورگر.",
            iconName = "check",
            htmlCode = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>تودولیست تیروتیر</title>
    <style>
        * { box-sizing: border-box; font-family: Tahoma, sans-serif; }
        body { background: #f8fafc; min-height: 100vh; display: flex; justify-content: center; padding: 40px 15px; }
        .todo-card { background: white; width: 100%; max-width: 380px; padding: 25px; border-radius: 16px; box-shadow: 0 10px 25px rgba(0,0,0,0.08); height: fit-content; }
        h2 { font-size: 20px; color: #1e293b; margin-bottom: 20px; text-align: center; }
        .input-row { display: flex; gap: 8px; margin-bottom: 20px; }
        input[type="text"] { flex: 1; padding: 10px; border: 1px solid #cbd5e1; border-radius: 8px; outline: none; }
        input[type="text"]:focus { border-color: #2563eb; }
        button.add-btn { background: #2563eb; color: white; border: none; padding: 10px 18px; border-radius: 8px; cursor: pointer; font-weight: bold; }
        ul.task-list { list-style: none; padding: 0; }
        li.task-item { display: flex; justify-content: space-between; align-items: center; padding: 10px 12px; background: #f1f5f9; border-radius: 8px; margin-bottom: 8px; }
        li.task-item.done span { text-decoration: line-through; color: #94a3b8; }
        .del-btn { background: #fee2e2; color: #dc2626; border: none; padding: 4px 8px; border-radius: 4px; cursor: pointer; font-size: 12px; }
    </style>
</head>
<body>
    <div class="todo-card">
        <h2>برنامه‌ریزی مطالعه تیروتیر</h2>
        <div class="input-row">
            <input type="text" id="taskInput" placeholder="وظیفه جدید را وارد کنید...">
            <button class="add-btn" onclick="addTask()">افزودن</button>
        </div>
        <ul class="task-list" id="taskList"></ul>
    </div>
    <script>
        let tasks = JSON.parse(localStorage.getItem('tir_tasks') || '[]');
        if (tasks.length === 0) {
            tasks = [
                { text: "مطالعه تگ‌های فرم در HTML5", done: true },
                { text: "تمرین Flexbox و Grid", done: false },
                { text: "حل آزمون جاوااسکریپت تیروتیر", done: false }
            ];
        }
        function saveAndRender() {
            localStorage.setItem('tir_tasks', JSON.stringify(tasks));
            const list = document.getElementById("taskList");
            list.innerHTML = "";
            tasks.forEach((t, i) => {
                const li = document.createElement("li");
                li.className = "task-item" + (t.done ? " done" : "");
                li.innerHTML = '<span onclick="toggleTask(' + i + ')" style="cursor:pointer; flex:1;">' + (t.done ? '✓ ' : '○ ') + t.text + '</span>' +
                               '<button class="del-btn" onclick="delTask(' + i + ')">حذف</button>';
                list.appendChild(li);
            });
        }
        function addTask() {
            const input = document.getElementById("taskInput");
            if (!input.value.trim()) return;
            tasks.push({ text: input.value.trim(), done: false });
            input.value = "";
            saveAndRender();
        }
        function toggleTask(i) {
            tasks[i].done = !tasks[i].done;
            saveAndRender();
        }
        function delTask(i) {
            tasks.splice(i, 1);
            saveAndRender();
        }
        saveAndRender();
    </script>
</body>
</html>"""
        ),
        WebProject(
            id = "proj_quote_generator",
            prefix = "hcj49",
            titleFa = "نقل‌قول‌های انگیزشی دنیای کدنویسی (Quote Generator)",
            categoryFa = "تعاملی و سرگرمی",
            descriptionFa = "نمایش تصادفی سخنان الهام‌بخش پیشگامان تکنولوژی به دو زبان فارسی و انگلیسی همراه با دکمه اشتراک‌گذاری.",
            iconName = "quote",
            htmlCode = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>جملات انگیزشی تیروتیر</title>
    <style>
        * { box-sizing: border-box; font-family: Tahoma, sans-serif; }
        body { background: linear-gradient(135deg, #1e1b4b, #4338ca); min-height: 100vh; display: flex; align-items: center; justify-content: center; padding: 20px; color: white; }
        .quote-box { background: rgba(255,255,255,0.1); backdrop-filter: blur(12px); border: 1px solid rgba(255,255,255,0.2); padding: 35px 25px; border-radius: 20px; max-width: 420px; text-align: center; box-shadow: 0 15px 35px rgba(0,0,0,0.3); }
        .quote-icon { font-size: 36px; margin-bottom: 10px; }
        .quote-fa { font-size: 17px; line-height: 1.8; margin-bottom: 12px; font-weight: bold; }
        .quote-en { font-size: 13px; color: #cbd5e1; direction: ltr; margin-bottom: 20px; font-style: italic; }
        .author { color: #38bdf8; font-size: 14px; margin-bottom: 25px; font-weight: bold; }
        .btn-row { display: flex; gap: 10px; justify-content: center; }
        button { background: #38bdf8; color: #0f172a; border: none; padding: 10px 20px; border-radius: 10px; cursor: pointer; font-weight: bold; font-size: 14px; }
    </style>
</head>
<body>
    <div class="quote-box">
        <div class="quote-icon">“</div>
        <div class="quote-fa" id="qFa">راه اندازی هر کاری با توقف حرف زدن و شروع به عمل کردن آغاز می‌شود.</div>
        <div class="quote-en" id="qEn">The best way to get started is to quit talking and begin doing.</div>
        <div class="author" id="qAuthor">— والت دیزنی</div>
        <div class="btn-row">
            <button onclick="newQuote()">جمله جدید 🎲</button>
        </div>
    </div>
    <script>
        const quotes = [
            { fa: "راه اندازی هر کاری با توقف حرف زدن و شروع به عمل کردن آغاز می‌شود.", en: "The best way to get started is to quit talking and begin doing.", author: "والت دیزنی" },
            { fa: "سادگی پیش‌نیاز قابلیت اعتماد در نرم‌افزار است.", en: "Simplicity is prerequisite for reliability.", author: "ادسخر دایکسترا" },
            { fa: "کدهایی بنویس که انگار فرد نگهدارنده آن یک انسان خشن است که آدرس خانه‌ات را بلد است!", en: "Always code as if the guy who ends up maintaining your code will be a violent psychopath who knows where you live.", author: "جان وودز" },
            { fa: "بهترین زمان برای کاشت یک درخت ۲۰ سال پیش بود؛ دومین زمان مناسب همین امروز است.", en: "The best time to plant a tree was 20 years ago. The second best time is now.", author: "ضرب‌المثل چینی" }
        ];
        function newQuote() {
            const r = Math.floor(Math.random() * quotes.length);
            document.getElementById("qFa").innerText = quotes[r].fa;
            document.getElementById("qEn").innerText = quotes[r].en;
            document.getElementById("qAuthor").innerText = "— " + quotes[r].author;
        }
    </script>
</body>
</html>"""
        ),
        WebProject(
            id = "proj_weight_converter",
            prefix = "hcj51",
            titleFa = "مبدل واحدهای جرم و وزن (Weight & Unit Converter)",
            categoryFa = "محاسباتی و ابزار",
            descriptionFa = "تبدیل بلادرنگ کیلوگرم به گرم، پوند و اونس با رابط کاربری شکیل تیروتیر.",
            iconName = "balance",
            htmlCode = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>مبدل وزن تیروتیر</title>
    <style>
        * { box-sizing: border-box; font-family: Tahoma, sans-serif; }
        body { background: #f0fdf4; min-height: 100vh; display: flex; justify-content: center; align-items: center; padding: 20px; }
        .card { background: white; padding: 30px; border-radius: 16px; box-shadow: 0 10px 25px rgba(0,0,0,0.06); width: 320px; text-align: center; }
        h2 { color: #15803d; margin-bottom: 20px; font-size: 20px; }
        input[type="number"] { width: 100%; padding: 12px; border: 2px solid #86efac; border-radius: 8px; font-size: 16px; outline: none; margin-bottom: 15px; }
        .result-box { background: #f8fafc; padding: 12px; border-radius: 8px; margin-top: 10px; text-align: right; font-size: 14px; }
        .res-item { padding: 4px 0; display: flex; justify-content: space-between; border-bottom: 1px dashed #e2e8f0; }
    </style>
</head>
<body>
    <div class="card">
        <h2>⚖️ مبدل وزن و جرم</h2>
        <input type="number" id="kgVal" placeholder="مقدار بر حسب کیلوگرم..." oninput="convertWeight()">
        <div class="result-box">
            <div class="res-item"><span>گرم:</span><b id="gOut">0 گرم</b></div>
            <div class="res-item"><span>پوند (lb):</span><b id="lbOut">0 پوند</b></div>
            <div class="res-item"><span>اونس (oz):</span><b id="ozOut">0 اونس</b></div>
        </div>
    </div>
    <script>
        function convertWeight() {
            const kg = parseFloat(document.getElementById("kgVal").value) || 0;
            document.getElementById("gOut").innerText = (kg * 1000).toLocaleString('fa-IR') + " گرم";
            document.getElementById("lbOut").innerText = (kg * 2.20462).toFixed(2) + " lb";
            document.getElementById("ozOut").innerText = (kg * 35.274).toFixed(2) + " oz";
        }
    </script>
</body>
</html>"""
        ),
        WebProject(
            id = "proj_financial_tools",
            prefix = "hcj54",
            titleFa = "ماشین‌حساب مالی و اقساط وام (Financial Tools & Mortgage)",
            categoryFa = "امور مالی و محاسبات",
            descriptionFa = "محاسبه قسط ماهیانه وام، سود بانکی و واجد شرایط بودن دریافت تسهیلات آموزشی.",
            iconName = "finance",
            htmlCode = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>محاسبه اقساط وام تیروتیر</title>
    <style>
        * { box-sizing: border-box; font-family: Tahoma, sans-serif; }
        body { background: #f8fafc; padding: 25px 15px; }
        .calc-card { background: white; max-width: 400px; margin: auto; padding: 25px; border-radius: 16px; box-shadow: 0 10px 25px rgba(0,0,0,0.06); }
        h2 { color: #0284c7; font-size: 20px; text-align: center; margin-bottom: 20px; }
        label { display: block; margin: 10px 0 4px; font-size: 13px; font-weight: bold; }
        input { width: 100%; padding: 10px; border: 1px solid #cbd5e1; border-radius: 8px; outline: none; }
        button { width: 100%; background: #0284c7; color: white; border: none; padding: 12px; border-radius: 8px; font-weight: bold; margin-top: 20px; cursor: pointer; }
        .res-panel { margin-top: 20px; padding: 15px; background: #e0f2fe; border-radius: 8px; font-size: 14px; text-align: center; font-weight: bold; color: #0369a1; }
    </style>
</head>
<body>
    <div class="calc-card">
        <h2>💰 برآورد اقساط شهریه و وام</h2>
        <label>مبلغ وام / شهریه (تومان):</label>
        <input type="number" id="amount" value="10000000">
        <label>نرخ کارمزد سالانه (%):</label>
        <input type="number" id="rate" value="18">
        <label>مدت بازپرداخت (ماه):</label>
        <input type="number" id="months" value="12">
        <button onclick="calcLoan()">محاسبه قسط ماهیانه</button>
        <div class="res-panel" id="loanResult">قسط ماهانه: ۸۴۰,۰۰۰ تومان</div>
    </div>
    <script>
        function calcLoan() {
            const P = parseFloat(document.getElementById("amount").value) || 0;
            const rYear = parseFloat(document.getElementById("rate").value) || 0;
            const n = parseFloat(document.getElementById("months").value) || 1;
            const r = (rYear / 100) / 12;
            let monthly = 0;
            if (r > 0) {
                monthly = (P * r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);
            } else {
                monthly = P / n;
            }
            document.getElementById("loanResult").innerText = "قسط ماهانه: " + Math.round(monthly).toLocaleString('fa-IR') + " تومان";
        }
    </script>
</body>
</html>"""
        ),
        WebProject(
            id = "proj_text_to_speech",
            prefix = "hcj45",
            titleFa = "سنتز و گوینده متن صوتی (Text-to-Speech)",
            categoryFa = "وب API و هوش مصنوعی",
            descriptionFa = "تبدیل آنی متن تایپ شده به گفتار صوتی با استفاده از Web Speech API و پشتیبانی درونی مرورگر.",
            iconName = "voice",
            htmlCode = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>تیدوتیر TTS</title>
    <style>
        * { box-sizing: border-box; font-family: Tahoma, sans-serif; }
        body { background: #0f172a; min-height: 100vh; display: flex; justify-content: center; align-items: center; padding: 20px; color: white; }
        .voice-card { background: #1e293b; padding: 25px; border-radius: 16px; width: 100%; max-width: 360px; text-align: center; box-shadow: 0 10px 30px rgba(0,0,0,0.5); }
        h2 { font-size: 20px; color: #38bdf8; margin-bottom: 15px; }
        textarea { width: 100%; height: 110px; background: #0f172a; color: #f8fafc; border: 1px solid #334155; border-radius: 10px; padding: 12px; font-size: 14px; outline: none; resize: none; margin-bottom: 15px; }
        button { background: #0284c7; color: white; border: none; padding: 12px 25px; border-radius: 8px; font-weight: bold; cursor: pointer; display: flex; align-items: center; justify-content: center; gap: 8px; margin: auto; }
        .status { margin-top: 10px; font-size: 12px; color: #94a3b8; }
    </style>
</head>
<body>
    <div class="voice-card">
        <h2>🎙️ گوینده هوشمند متن</h2>
        <textarea id="textInput" placeholder="متن انگلیسی یا فارسی خود را جهت قرائت صوتی وارد کنید...">Welcome to tirOtir AI Academy. Happy coding!</textarea>
        <button onclick="speakNow()"><span>🔊</span> پخش صوتی</button>
        <div class="status" id="speechStatus">آماده قرائت</div>
    </div>
    <script>
        function speakNow() {
            const text = document.getElementById("textInput").value;
            if (!text.trim()) return;
            if ('speechSynthesis' in window) {
                window.speechSynthesis.cancel();
                const u = new SpeechSynthesisUtterance(text);
                document.getElementById("speechStatus").innerText = "در حال پخش...";
                u.onend = () => document.getElementById("speechStatus").innerText = "قرائت پایان یافت";
                window.speechSynthesis.speak(u);
            } else {
                document.getElementById("speechStatus").innerText = "مرورگر از Speech Synthesis پشتیبانی نمی‌کند.";
            }
        }
    </script>
</body>
</html>"""
        ),
        WebProject(
            id = "proj_marquee_hub",
            prefix = "hcj77",
            titleFa = "انیمیشن‌های بنر و متن روان (Marquee Hub)",
            categoryFa = "افکت‌های CSS",
            descriptionFa = "مجموعه‌ای از نوارهای متحرک خبری، قیمت لحظه‌ای، اسکرول رفت و برگشتی و ایست با هاور.",
            iconName = "ticker",
            htmlCode = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>متن‌های متحرک تیروتیر</title>
    <style>
        * { box-sizing: border-box; font-family: Tahoma, sans-serif; }
        body { background: #0f172a; color: white; padding: 30px 15px; }
        h2 { text-align: center; color: #38bdf8; margin-bottom: 25px; }
        .marquee-box { background: #1e293b; padding: 15px; border-radius: 10px; margin-bottom: 15px; border: 1px solid rgba(255,255,255,0.06); }
        .label { font-size: 13px; color: #94a3b8; margin-bottom: 8px; }
        marquee { font-size: 16px; font-weight: bold; color: #a5f3fc; }
    </style>
</head>
<body>
    <h2>نمونه‌های متحرک‌سازی متن در وب</h2>
    <div class="marquee-box">
        <div class="label">۱. نوار اخبار فوری آکادمی (پیوسته):</div>
        <marquee direction="right" scrollamount="6">🚀 ثبت نام ترم پاییز آموزشگاه هوش مصنوعی و فنی‌حرفه‌ای تیروتیر آغاز گردید.</marquee>
    </div>
    <div class="marquee-box">
        <div class="label">۲. رفت و برگشتی (Alternate):</div>
        <marquee behavior="alternate" scrollamount="8">⭐ یادگیری صفر تا صد وب دولوپمنت با مدرک معتبر بین‌المللی ⭐</marquee>
    </div>
    <div class="marquee-box">
        <div class="label">۳. توقف هنگام قرارگیری نشانگر ماوس:</div>
        <marquee onmouseover="this.stop();" onmouseout="this.start();" scrollamount="5">برای توقف نوار، ماوس را روی آن نگه دارید.</marquee>
    </div>
</body>
</html>"""
        )
    )

    fun getProjectById(id: String): WebProject? {
        return projects.find { it.id == id }
    }
}
