package com.example.data.model

object CurriculumRepository {

    val modules: List<ModuleInfo> = listOf(
        ModuleInfo(
            id = 1,
            titleFa = "فصل ۱: مبانی HTML5 و ساختار وب معنایی",
            subtitleFa = "اسکلت‌بندی صفحات، ساختار سند، فرم‌ها و تگ‌های معنایی مدرن",
            iconName = "html",
            snippetRange = "hcj00 - hcj04, hcj34, hcj35, hcj63",
            colorHex = 0xFFE44D26
        ),
        ModuleInfo(
            id = 2,
            titleFa = "فصل ۲: استایل‌دهی مدرن با CSS3 و سیستم‌های چیدمان",
            subtitleFa = "مدل جعبه‌ای، Flexbox، Grid، گرادینت‌ها و انیمیشن‌ها",
            iconName = "css",
            snippetRange = "hcj01, hcj18-19, hcj23, hcj26-29, hcj33, hcj36-37, hcj41, hcj58, hcj64",
            colorHex = 0xFF264DE4
        ),
        ModuleInfo(
            id = 3,
            titleFa = "فصل ۳: موتور برنامه‌نویسی جاوااسکریپت مدرن (ES6+)",
            subtitleFa = "متغیرها، توابع فلشی، حلقه‌ها، متدهای آرایه و برنامه‌نویسی غیرهمگام",
            iconName = "javascript",
            snippetRange = "hcj05-07, hcj14-17, hcj20-21, hcj38-40, hcj42, hcj53, hcj55, hcj57, hcj59, hcj60, hcj62, hcj65",
            colorHex = 0xFFF7DF1E
        ),
        ModuleInfo(
            id = 4,
            titleFa = "فصل ۴: کار با DOM و وب APIهای تعاملی",
            subtitleFa = "دستکاری عناصر صفحه، رویدادها، نقشه گوگل، Speech API و موقعیت‌یاب",
            iconName = "dom",
            snippetRange = "hcj06, hcj13, hcj16, hcj22, hcj45, hcj52, hcj61",
            colorHex = 0xFF009688
        ),
        ModuleInfo(
            id = 5,
            titleFa = "فصل ۵: کتابخانه جی‌کوئری (jQuery) و انیمیشن‌ها",
            subtitleFa = "پیمایش DOM، افکت‌های تصویری slide و fade، و مدیریت رویدادها",
            iconName = "jquery",
            snippetRange = "hcj66, hcj67",
            colorHex = 0xFF0769AD
        ),
        ModuleInfo(
            id = 6,
            titleFa = "فصل ۶: استانداردهای حرفه‌ای فرانت‌اند، سئو و بهینه‌سازی",
            subtitleFa = "۲۰ اصل توسعه استاندارد، متاتگ‌های Open Graph، ARIA و کارایی",
            iconName = "seo",
            snippetRange = "hcj78, hcj79",
            colorHex = 0xFF8E24AA
        )
    )

    val lessons: List<Lesson> = listOf(
        // MODULE 1: HTML5
        Lesson(
            id = "m1_hcj00",
            moduleId = 1,
            prefix = "hcj00",
            titleFa = "ساختار پایه سند HTML5",
            titleEn = "Basic HTML Structure",
            descriptionFa = "ساختار استاندارد یک سند وب شامل تگ‌های doctype، html، head و body. نقطه شروع هر پروژه وب است.",
            codeSnippet = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>اولین صفحه وب من - آکادمی تیروتیر</title>
</head>
<body>
    <h1>سلام دنیا! به آکادمی تیروتیر خوش آمدید</h1>
    <p>این نخستین سند وب است که با ساختار استاندارد HTML5 ایجاد شده است.</p>
</body>
</html>""",
            exerciseFa = "متن داخل تگ <h1> را به 'آموزشگاه هوش مصنوعی تیروتیر' تغییر داده و یک تگ <h2> در زیر آن اضافه کنید.",
            solutionFa = "<h1>آموزشگاه هوش مصنوعی تیروتیر</h1>\n<h2>دوره جامع فرانت‌اند</h2>",
            tags = listOf("HTML5", "Doctype", "Head", "Body")
        ),
        Lesson(
            id = "m1_hcj02",
            moduleId = 1,
            prefix = "hcj02",
            titleFa = "عناصر اصلی HTML با استایل‌های پایه",
            titleEn = "Basic HTML Structure with CSS",
            descriptionFa = "استفاده از تگ‌های پرکاربرد شامل nav، ul، ol، جدول، نقل‌قول (blockquote) و فرم ساده.",
            codeSnippet = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>تگ‌های پرکاربرد HTML</title>
    <style>
        body { font-family: Tahoma, sans-serif; background-color: #f4f6f9; padding: 20px; }
        h1 { color: #1e3a8a; text-align: center; }
        nav { text-align: center; margin: 15px 0; }
        nav a { margin: 0 10px; color: #2563eb; text-decoration: none; font-weight: bold; }
        blockquote { border-right: 4px solid #2563eb; padding: 10px; background: #e0e7ff; }
        table { width: 100%; border-collapse: collapse; margin: 15px 0; }
        th, td { border: 1px solid #cbd5e1; padding: 8px; text-align: center; }
        th { background-color: #3b82f6; color: white; }
    </style>
</head>
<body>
    <h1>تیروتیر وب - آشنایی با تگ‌های متنی</h1>
    <nav>
        <a href="#home">خانه</a> | <a href="#courses">دوره‌ها</a> | <a href="#contact">تماس</a>
    </nav>
    <blockquote>آموزش برنامه‌نویسی وب از صفر تا صد در آکادمی تیروتیر</blockquote>
    <table>
        <tr><th>درس</th><th>تکنولوژی</th><th>وضعیت</th></tr>
        <tr><td>جلسه ۱</td><td>HTML5</td><td>تکمیل شده</td></tr>
        <tr><td>جلسه ۲</td><td>CSS3</td><td>در حال مطالعه</td></tr>
    </table>
</body>
</html>""",
            exerciseFa = "یک ردیف جدید به جدول با عنوان 'جلسه ۳ | JavaScript | در نوبت' بیفزایید.",
            solutionFa = "<tr><td>جلسه ۳</td><td>JavaScript</td><td>در نوبت</td></tr>",
            tags = listOf("HTML5", "Table", "Nav", "Blockquote")
        ),
        Lesson(
            id = "m1_hcj04",
            moduleId = 1,
            prefix = "hcj04",
            titleFa = "تگ‌های پیشرفته HTML5 و لیست‌ها",
            titleEn = "HTML Structure with Additional Tags",
            descriptionFa = "کار با انواع لیست‌های مرتب (ol) و نامرتب (ul)، نمایش تصاویر و فیلدهای ورودی کاربر.",
            codeSnippet = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>تگ‌های چندرسانه‌ای و لیست‌ها</title>
</head>
<body>
    <h2>فهرست سرفصل‌های بوت‌کمپ تیروتیر:</h2>
    <ul>
        <li>مفاهیم فرانت‌اند و معماری کلاینت</li>
        <li>طراحی واکنش‌گرا (Responsive)</li>
        <li>برنامه‌نویسی جاوااسکریپت و تعامل با DOM</li>
    </ul>
    <h3>مراحل اخذ مدرک فنی‌حرفه‌ای:</h3>
    <ol>
        <li>شرکت در کلاس‌ها و پروژه‌ها</li>
        <li>آزمون کتبی و عملی</li>
        <li>دریافت مدرک معتبر بین‌المللی</li>
    </ol>
</body>
</html>""",
            exerciseFa = "یک مورد چهارم به لیست نامرتب با عنوان 'پروژه نهایی فول‌استک' اضافه کنید.",
            solutionFa = "<li>پروژه نهایی فول‌استک</li>",
            tags = listOf("HTML5", "List", "Ordered List", "Unordered List")
        ),
        Lesson(
            id = "m1_hcj34",
            moduleId = 1,
            prefix = "hcj34",
            titleFa = "کنترل‌های فرم در HTML5",
            titleEn = "HTML Forms Fundamentals",
            descriptionFa = "ایجاد فرم‌های ورود اطلاعات، انواع فیلدهای ورودی (input)، برچسب‌ها (label) و دکمه ارسال.",
            codeSnippet = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <title>فرم ثبت نام کارآموزان</title>
    <style>
        body { font-family: Tahoma, sans-serif; padding: 20px; }
        .form-group { margin-bottom: 15px; }
        label { display: block; margin-bottom: 5px; font-weight: bold; }
        input[type="text"], input[type="email"] { width: 100%; max-width: 350px; padding: 8px; border: 1px solid #ccc; border-radius: 4px; }
        input[type="submit"] { background: #0284c7; color: white; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; }
    </style>
</head>
<body>
    <h3>فرم ثبت نام دوره فرانت‌اند تیروتیر</h3>
    <form action="#" method="POST">
        <div class="form-group">
            <label for="name">نام و نام خانوادگی:</label>
            <input type="text" id="name" name="name" placeholder="مثال: علی رضایی" required>
        </div>
        <div class="form-group">
            <label for="email">پست الکترونیک:</label>
            <input type="email" id="email" name="email" placeholder="example@domain.com" required>
        </div>
        <input type="submit" value="ارسال درخواست ثبت‌نام">
    </form>
</body>
</html>""",
            exerciseFa = "یک فیلد شماره همراه با نوع 'tel' به فرم اضافه کنید.",
            solutionFa = "<label for='phone'>شماره تماس:</label><input type='tel' id='phone' name='phone' placeholder='0912...'>",
            tags = listOf("HTML5", "Forms", "Input", "Submit")
        ),
        Lesson(
            id = "m1_hcj35",
            moduleId = 1,
            prefix = "hcj35",
            titleFa = "فیلدهای ورودی پیشرفته فرم (تاریخ، چک‌باکس، رادیو)",
            titleEn = "Advanced HTML Form Inputs",
            descriptionFa = "استفاده از فیلدهای پسورد، انتخاب چندگزینه‌ای (radio)، چک‌باکس قوانین، لیست آبشاری (select) و تاریخ.",
            codeSnippet = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <title>فرم کامل مشخصات</title>
    <style>
        body { font-family: Tahoma, sans-serif; padding: 20px; line-height: 1.8; }
    </style>
</head>
<body>
    <h3>انتخاب‌های دوره تیروتیر</h3>
    <form>
        <label>دوره مورد علاقه:</label><br>
        <input type="radio" id="web" name="course" value="web" checked>
        <label for="web">برنامه‌نویسی وب</label>
        <input type="radio" id="ai" name="course" value="ai">
        <label for="ai">هوش مصنوعی و پایتون</label>
        <br><br>
        <label for="level">سطح دانش فعلی:</label>
        <select id="level">
            <option value="beginner">مبتدی (صفر)</option>
            <option value="intermediate">متوسط</option>
            <option value="advanced">پیشرفته</option>
        </select>
        <br><br>
        <label>
            <input type="checkbox" required> با قوانین آموزشی موسسه اندیشه پردازان آنزان موافقم
        </label>
        <br><br>
        <button type="submit">ثبت نهایی</button>
    </form>
</body>
</html>""",
            exerciseFa = "یک چک‌باکس دیگر برای 'عضویت در خبرنامه تلگرام تیروتیر' قرار دهید.",
            solutionFa = "<label><input type='checkbox' name='newsletter'> عضویت در خبرنامه تلگرام تیروتیر</label>",
            tags = listOf("HTML5", "Forms", "Radio", "Select", "Checkbox")
        ),
        Lesson(
            id = "m1_hcj63",
            moduleId = 1,
            prefix = "hcj63",
            titleFa = "چیت‌شیت جامع تگ‌های HTML5 (راهنمای مرجع)",
            titleEn = "HTML Cheat Sheet",
            descriptionFa = "مرجع کامل و جامع بیش از ۴۰ تگ کلیدی HTML5 شامل تگ‌های معنایی، صوتی و تصویری، دیتالیست، متغیرها، نشانه‌گذاری و دیاگرام.",
            codeSnippet = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <title>چیت‌شیت HTML5 - آکادمی تیروتیر</title>
    <style>
        body { font-family: Tahoma, sans-serif; padding: 20px; background: #fafafa; }
        .tag-card { background: white; padding: 12px; margin: 8px 0; border-radius: 6px; box-shadow: 0 1px 3px rgba(0,0,0,0.1); }
        code { background: #f1f5f9; color: #0f172a; padding: 2px 6px; border-radius: 4px; font-weight: bold; }
    </style>
</head>
<body>
    <h1>راهنمای سریع تگ‌های استاندارد HTML5</h1>
    <div class="tag-card"><code>&lt;header&gt;</code>, <code>&lt;nav&gt;</code>, <code>&lt;main&gt;</code>, <code>&lt;footer&gt;</code> - اسکلت معنایی صفحه</div>
    <div class="tag-card"><code>&lt;article&gt;</code>, <code>&lt;section&gt;</code>, <code>&lt;aside&gt;</code> - بخش‌بندی محتوا</div>
    <div class="tag-card"><code>&lt;mark&gt;</code>: <mark>متن هایلایت شده</mark> | <code>&lt;del&gt;</code>: <del>متن حذف شده</del></div>
    <div class="tag-card">
        <code>&lt;details&gt;</code> و <code>&lt;summary&gt;</code>:
        <details>
            <summary>کلیک برای مشاهده جزئیات آکادمی تیروتیر</summary>
            <p>آموزشگاه فنی و حرفه‌ای تیروتیر، ارائه دهنده مدارک رسمی و آموزش‌های بازار محور.</p>
        </details>
    </div>
    <div class="tag-card">
        <code>&lt;progress&gt;</code> پیشرفت دوره:
        <progress value="75" max="100"></progress> 75%
    </div>
</body>
</html>""",
            exerciseFa = "یک تگ <meter> با مقدار 0.8 و بازه 0 تا 1 در صفحه تعبیه کنید.",
            solutionFa = "<meter value='0.8' min='0' max='1'>80%</meter>",
            tags = listOf("HTML5", "Cheat Sheet", "Semantic", "Audio", "Video")
        ),

        // MODULE 2: CSS3
        Lesson(
            id = "m2_hcj01",
            moduleId = 2,
            prefix = "hcj01",
            titleFa = "مبانی استایل‌دهی با CSS (رنگ، فونت، چینش)",
            titleEn = "Styling Text with CSS",
            descriptionFa = "معرفی خصوصیات فونت، رنگ متن، جهت نگارش و ترازبندی عناصر در صفحه.",
            codeSnippet = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <title>آموزش استایل متون</title>
    <style>
        body { font-family: Tahoma, sans-serif; text-align: center; background-color: #f8fafc; padding: 40px; }
        h1 { color: #1d4ed8; font-size: 28px; text-shadow: 1px 1px 3px rgba(0,0,0,0.15); }
        p { color: #475569; font-size: 16px; line-height: 1.8; }
    </style>
</head>
<body>
    <h1>آکادمی هوش مصنوعی و وب تیروتیر</h1>
    <p>اینجا یاد می‌گیرید چگونه کدهای ساده را با CSS به رابط‌های کاربری چشم‌نواز تبدیل کنید.</p>
</body>
</html>""",
            exerciseFa = "رنگ عنوان h1 را به سبز #059669 تغییر دهید.",
            solutionFa = "h1 { color: #059669; }",
            tags = listOf("CSS3", "Typography", "Color", "Text Align")
        ),
        Lesson(
            id = "m2_hcj18",
            moduleId = 2,
            prefix = "hcj18",
            titleFa = "تغییر پویا کلاس‌های رنگی با CSS",
            titleEn = "Changing Text Color with CSS",
            descriptionFa = "تعریف کلاس‌های رنگی مجزا و تغییر دادن ظاهر المان‌ها به صورت هماهنگ.",
            codeSnippet = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <title>کلاس‌های رنگی</title>
    <style>
        .badge-red { color: #dc2626; background: #fee2e2; padding: 6px 12px; border-radius: 20px; font-weight: bold; }
        .badge-green { color: #16a34a; background: #dcfce7; padding: 6px 12px; border-radius: 20px; font-weight: bold; }
        .badge-blue { color: #2563eb; background: #dbeafe; padding: 6px 12px; border-radius: 20px; font-weight: bold; }
    </style>
</head>
<body style="font-family: Tahoma; padding: 20px;">
    <h2>وضعیت دوره‌های تیروتیر:</h2>
    <p>دوره HTML5: <span class="badge-green">فعال و در دسترس</span></p>
    <p>دوره React: <span class="badge-blue">به زودی</span></p>
    <p>ثبت نام حضوری: <span class="badge-red">تکمیل ظرفیت</span></p>
</body>
</html>""",
            exerciseFa = "یک کلاس badge-purple با رنگ بنفش و پس‌زمینه روشن ایجاد کنید.",
            solutionFa = ".badge-purple { color: #7c3aed; background: #ede9fe; padding: 6px 12px; border-radius: 20px; font-weight: bold; }",
            tags = listOf("CSS3", "Classes", "Badges")
        ),
        Lesson(
            id = "m2_hcj26",
            moduleId = 2,
            prefix = "hcj26",
            titleFa = "چیدمان شبکه‌ای مدرن (CSS Grid Layout)",
            titleEn = "CSS Grid Layout",
            descriptionFa = "ساخت شبکه‌های دو بعدی منظم با grid-template-columns، repeat، fr و gap برای طراحی واکنش‌گرا.",
            codeSnippet = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <title>سیستم گرید در CSS</title>
    <style>
        body { font-family: Tahoma, sans-serif; padding: 20px; background: #f1f5f9; }
        .grid-container {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(180px, 1fr));
            gap: 15px;
        }
        .grid-item {
            background: white;
            padding: 25px 15px;
            text-align: center;
            border-radius: 10px;
            box-shadow: 0 4px 6px -1px rgba(0,0,0,0.1);
            border-top: 4px solid #3b82f6;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <h2>مهارت‌های آموزش داده شده در تیروتیر:</h2>
    <div class="grid-container">
        <div class="grid-item">HTML5 Semantic</div>
        <div class="grid-item">CSS3 Flex & Grid</div>
        <div class="grid-item">Modern JavaScript</div>
        <div class="grid-item">DOM & Web APIs</div>
        <div class="grid-item">Responsive Design</div>
        <div class="grid-item">Git & GitHub</div>
    </div>
</body>
</html>""",
            exerciseFa = "تعداد ستون‌ها را با استفاده از 'repeat(3, 1fr)' تنظیم کنید.",
            solutionFa = "grid-template-columns: repeat(3, 1fr);",
            tags = listOf("CSS3", "Grid", "Layout", "Responsive")
        ),
        Lesson(
            id = "m2_hcj27",
            moduleId = 2,
            prefix = "hcj27",
            titleFa = "تبدیل‌ها و ترانسفورم‌های دو بعدی و سه بعدی",
            titleEn = "CSS Transformations",
            descriptionFa = "استفاده از rotate، scale، translate و transition برای ایجاد تعاملات حرکتی جذاب هنگام هاور ماوس.",
            codeSnippet = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <title>انیمیشن‌های هاور</title>
    <style>
        body { font-family: Tahoma; display: flex; justify-content: center; align-items: center; min-height: 80vh; background: #0f172a; color: white; }
        .card {
            width: 140px; height: 140px;
            background: linear-gradient(135deg, #06b6d4, #3b82f6);
            display: flex; align-items: center; justify-content: center;
            border-radius: 16px;
            cursor: pointer;
            transition: transform 0.4s cubic-bezier(0.175, 0.885, 0.32, 1.275);
            font-weight: bold;
            box-shadow: 0 10px 25px rgba(6,182,212,0.3);
        }
        .card:hover {
            transform: scale(1.15) rotate(8deg);
        }
    </style>
</head>
<body>
    <div class="card">روی من لمس کنید!</div>
</body>
</html>""",
            exerciseFa = "زاویه چرخش را به -10deg تغییر دهید تا در جهت عکس عقربه‌های ساعت بچرخد.",
            solutionFa = "transform: scale(1.15) rotate(-10deg);",
            tags = listOf("CSS3", "Transform", "Transition", "Rotate", "Scale")
        ),
        Lesson(
            id = "m2_hcj33",
            moduleId = 2,
            prefix = "hcj33",
            titleFa = "تکنیک‌های مرکزچینی و Flexbox مدرن",
            titleEn = "CSS Flexbox for Centering Content",
            descriptionFa = "مرکز کردن افقی و عمودی با display: flex، justify-content و align-items در کمترین خط کد.",
            codeSnippet = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <title>فلکس‌باکس و مرکزچینی</title>
    <style>
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 90vh;
            margin: 0;
            background: #e2e8f0;
            font-family: Tahoma, sans-serif;
        }
        .center-card {
            background: white;
            padding: 30px 40px;
            border-radius: 12px;
            box-shadow: 0 10px 15px -3px rgba(0,0,0,0.1);
            text-align: center;
        }
        h2 { color: #0f172a; margin-top: 0; }
        p { color: #64748b; }
    </style>
</head>
<body>
    <div class="center-card">
        <h2>کاملاً در مرکز صفحه</h2>
        <p>با ترکیب justify-content و align-items در حالت Flexbox</p>
    </div>
</body>
</html>""",
            exerciseFa = "جهت فلکس‌باکس را با flex-direction: column به صورت ستونی تعریف کنید.",
            solutionFa = "flex-direction: column;",
            tags = listOf("CSS3", "Flexbox", "Centering", "Alignment")
        ),
        Lesson(
            id = "m2_hcj36",
            moduleId = 2,
            prefix = "hcj36",
            titleFa = "گرادینت‌های خطی و دایره‌ای (Linear & Radial Gradients)",
            titleEn = "CSS Gradient Backgrounds",
            descriptionFa = "ایجاد پس‌زمینه‌های گرادینت چند رنگی با زوایای مختلف، ایستگاه‌های رنگی و گرادینت مدور.",
            codeSnippet = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <title>گرادینت در CSS</title>
    <style>
        body {
            margin: 0;
            height: 100vh;
            background: linear-gradient(135deg, #6366f1 0%, #a855f7 50%, #ec4899 100%);
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: Tahoma, sans-serif;
            color: white;
        }
        .glass-box {
            background: rgba(255, 255, 255, 0.15);
            backdrop-filter: blur(10px);
            padding: 30px;
            border-radius: 16px;
            border: 1px solid rgba(255, 255, 255, 0.2);
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="glass-box">
        <h2>پس‌زمینه گرادینت مدرن تیروتیر</h2>
        <p>ترکیب رنگ‌های نئونی ایندیگو، بنفش و سرخابی</p>
    </div>
</body>
</html>""",
            exerciseFa = "گرادینت را به radial-gradient(circle, #3b82f6, #1e1b4b) تغییر دهید.",
            solutionFa = "background: radial-gradient(circle, #3b82f6, #1e1b4b);",
            tags = listOf("CSS3", "Gradient", "Linear", "Radial", "Glassmorphism")
        ),
        Lesson(
            id = "m2_hcj37",
            moduleId = 2,
            prefix = "hcj37",
            titleFa = "انیمیشن‌های کلیدی (@keyframes) در CSS",
            titleEn = "CSS Keyframes Animation",
            descriptionFa = "ساخت انیمیشن‌های روان و پیوسته با @keyframes، تنظیم duration و زمان‌بندی infinite.",
            codeSnippet = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <title>انیمیشن با Keyframes</title>
    <style>
        body { display: flex; justify-content: center; align-items: center; height: 80vh; background: #0b0f19; font-family: Tahoma; }
        @keyframes pulseGlow {
            0% { transform: scale(1); box-shadow: 0 0 0 0 rgba(59, 130, 246, 0.7); }
            70% { transform: scale(1.08); box-shadow: 0 0 0 20px rgba(59, 130, 246, 0); }
            100% { transform: scale(1); box-shadow: 0 0 0 0 rgba(59, 130, 246, 0); }
        }
        .pulse-box {
            width: 120px; height: 120px;
            background: #2563eb;
            color: white;
            display: flex; align-items: center; justify-content: center;
            border-radius: 50%;
            animation: pulseGlow 2s infinite;
            font-weight: bold;
        }
    </style>
</head>
<body>
    <div class="pulse-box">تیروتیر وب</div>
</body>
</html>""",
            exerciseFa = "مدت زمان انیمیشن را از 2s به 1s تغییر دهید تا تندتر تپش کند.",
            solutionFa = "animation: pulseGlow 1s infinite;",
            tags = listOf("CSS3", "Keyframes", "Animation", "Pulse")
        ),
        Lesson(
            id = "m2_hcj58",
            moduleId = 2,
            prefix = "hcj58",
            titleFa = "شبه‌کلاس‌ها (Pseudo-Classes) در CSS",
            titleEn = "CSS Pseudo-Classes Mastery",
            descriptionFa = "کاربرد :hover، :active، :focus، :nth-child، :not و :disabled در تعاملی کردن المان‌ها.",
            codeSnippet = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <title>شبه‌کلاس‌های CSS</title>
    <style>
        body { font-family: Tahoma; padding: 20px; }
        ul { list-style: none; padding: 0; }
        li { padding: 10px; margin: 4px 0; border-radius: 4px; }
        li:nth-child(odd) { background: #e0f2fe; }
        li:nth-child(even) { background: #f0fdf4; }
        li:hover { background: #fef08a; cursor: pointer; }
        input:focus { outline: 2px solid #2563eb; background: #eff6ff; }
        button:disabled { opacity: 0.5; cursor: not-allowed; }
    </style>
</head>
<body>
    <h3>لیست با شبه‌کلاس nth-child:</h3>
    <ul>
        <li>هنرجو ۱: فاطمه حسینی</li>
        <li>هنرجو ۲: رضا محمدی</li>
        <li>هنرجو ۳: سارا احمدی</li>
        <li>هنرجو ۴: مهدی کاظمی</li>
    </ul>
    <input type="text" placeholder="فوکوس کنید تا کادر آبی شود"><br><br>
    <button disabled>دکمه غیرفعال</button>
</body>
</html>""",
            exerciseFa = "با استفاده از li:first-child به اولین سطر حاشیه ضخیم بدهید.",
            solutionFa = "li:first-child { border: 2px solid #0284c7; }",
            tags = listOf("CSS3", "Pseudo-classes", "Hover", "Nth-child")
        ),
        Lesson(
            id = "m2_hcj64",
            moduleId = 2,
            prefix = "hcj64",
            titleFa = "چیت‌شیت جامع CSS3 (مرجع کامل استایل‌ها)",
            titleEn = "CSS Cheat Sheet",
            descriptionFa = "مرجع کامل خواص پرکاربرد CSS3 شامل تایپوگرافی، مدل جعبه‌ای، ترانزیشن، انیمیشن و فیلترها.",
            codeSnippet = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <title>چیت‌شیت CSS3 - تیروتیر</title>
    <style>
        body { font-family: Tahoma, sans-serif; padding: 20px; background: #f8fafc; }
        .section { background: white; padding: 15px; margin-bottom: 12px; border-radius: 8px; box-shadow: 0 1px 3px rgba(0,0,0,0.08); }
        .code-prop { color: #dc2626; font-weight: bold; }
        .code-val { color: #2563eb; }
    </style>
</head>
<body>
    <h1>مرجع سریع دستورات CSS3</h1>
    <div class="section">
        <h3>۱. تنظیمات جعبه و فاصله</h3>
        <p><span class="code-prop">box-sizing:</span> <span class="code-val">border-box;</span></p>
        <p><span class="code-prop">box-shadow:</span> <span class="code-val">0 10px 15px rgba(0,0,0,0.1);</span></p>
        <p><span class="code-prop">border-radius:</span> <span class="code-val">12px;</span></p>
    </div>
    <div class="section">
        <h3>۲. چیدمان مدرن</h3>
        <p><span class="code-prop">display:</span> <span class="code-val">flex | grid;</span></p>
        <p><span class="code-prop">justify-content:</span> <span class="code-val">center | space-between;</span></p>
    </div>
</body>
</html>""",
            exerciseFa = "خاصیت filter: blur(4px) را بررسی کنید.",
            solutionFa = "img { filter: blur(4px); }",
            tags = listOf("CSS3", "Cheat Sheet", "Reference")
        ),

        // MODULE 3: JAVASCRIPT ES6+
        Lesson(
            id = "m3_hcj05",
            moduleId = 3,
            prefix = "hcj05",
            titleFa = "متغیرها و انواع داده اولیه در JavaScript",
            titleEn = "Variables and Data Types in JS",
            descriptionFa = "تفاوت let، const، var و انواع داده شامل Number، String، Boolean، Object و Array.",
            codeSnippet = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <title>متغیرهای جاوااسکریپت</title>
</head>
<body style="font-family: Tahoma; padding: 20px;">
    <h2>خروجی متغیرهای جاوااسکریپت:</h2>
    <div id="output" style="background: #f1f5f9; padding: 15px; border-radius: 8px; font-family: monospace;"></div>

    <script>
        const academyName = "آموزشگاه هوش مصنوعی و وب تیروتیر";
        let foundedYear = 1379;
        let isCertified = true;
        let courses = ["HTML", "CSS", "JavaScript", "React", "Python"];
        let instructor = { name: "تیم تخصصی تیروتیر", experience: 24 };

        const message = `نام آموزشگاه: ` + academyName + `\n` +
                        `سال تاسیس: ` + foundedYear + `\n` +
                        `مجوز رسمی: ` + (isCertified ? 'دارد' : 'ندارد') + `\n` +
                        `دوره‌ها: ` + courses.join(' | ') + `\n` +
                        `مدرس: ` + instructor.name;

        document.getElementById("output").innerText = message;
    </script>
</body>
</html>""",
            exerciseFa = "یک متغیر const جدید به نام studentScore تعریف کرده و در خروجی نمایش دهید.",
            solutionFa = "const studentScore = 100;\n// اضافه کردن به متن خروجی",
            tags = listOf("JavaScript", "Variables", "let", "const", "Data Types")
        ),
        Lesson(
            id = "m3_hcj21",
            moduleId = 3,
            prefix = "hcj21",
            titleFa = "دستورات شرطی و عملگر سه‌تایی (Ternary)",
            titleEn = "Conditional Statements in JavaScript",
            descriptionFa = "تصمیم‌گیری در کد با if/else، switch-case و عبارت‌های شرطی فشرده (condition ? true : false).",
            codeSnippet = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <title>شرط‌ها در جاوااسکریپت</title>
</head>
<body style="font-family: Tahoma; padding: 20px;">
    <h2>محاسبه نمره و سطح قبولی:</h2>
    <p id="gradeResult"></p>

    <script>
        let score = 88;
        let resultText = "";

        if (score >= 90) {
            resultText = "نمره " + score + ": عالی (سطح A+)";
        } else if (score >= 75) {
            resultText = "نمره " + score + ": خیلی خوب - قبول شده در آزمون تیروتیر (سطح B)";
        } else if (score >= 50) {
            resultText = "نمره " + score + ": نیاز به تمرین بیشتر (سطح C)";
        } else {
            resultText = "نمره " + score + ": مردود";
        }

        document.getElementById("gradeResult").innerText = resultText;
    </script>
</body>
</html>""",
            exerciseFa = "مقدار score را به 95 تغییر داده و خروجی را بررسی کنید.",
            solutionFa = "let score = 95;",
            tags = listOf("JavaScript", "Conditionals", "if-else", "Ternary")
        ),
        Lesson(
            id = "m3_hcj57",
            moduleId = 3,
            prefix = "hcj57",
            titleFa = "تسلط بر متدهای آرایه (.map, .filter, .reduce)",
            titleEn = "JavaScript Array Methods",
            descriptionFa = "پردازش حرفه‌ای مجموعه‌ها با متدهای مدرن تابعی در جاوااسکریپت ES6.",
            codeSnippet = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <title>متدهای آرایه در جاوااسکریپت</title>
</head>
<body style="font-family: Tahoma; padding: 20px;">
    <h2>پردازش آرایه‌ها با متدهای تابعی:</h2>
    <pre id="codeOut" style="background: #0f172a; color: #38bdf8; padding: 15px; border-radius: 8px;"></pre>

    <script>
        const numbers = [10, 25, 30, 45, 60, 75, 90];

        // ۱. فیلتر کردن اعداد بالای ۴۰
        const filtered = numbers.filter(n => n > 40);

        // ۲. دو برابر کردن مقادیر با map
        const doubled = filtered.map(n => n * 2);

        // ۳. جمع کل مقادیر با reduce
        const sum = numbers.reduce((total, curr) => total + curr, 0);

        const report = "اعداد اولیه: " + numbers.join(', ') + "\n" +
                       "فیلتر بالای ۴۰: " + filtered.join(', ') + "\n" +
                       "دو برابر شده‌ها: " + doubled.join(', ') + "\n" +
                       "مجموع کل اعداد: " + sum;

        document.getElementById("codeOut").innerText = report;
    </script>
</body>
</html>""",
            exerciseFa = "با استفاده از .find اولین عدد فرد در آرایه را بیابید.",
            solutionFa = "const firstOdd = numbers.find(n => n % 2 !== 0);",
            tags = listOf("JavaScript", "Array", "map", "filter", "reduce", "ES6")
        ),
        Lesson(
            id = "m3_hcj60",
            moduleId = 3,
            prefix = "hcj60",
            titleFa = "برنامه‌نویسی غیرهمگام: تایمرها، Promises و Async/Await",
            titleEn = "Asynchronous JavaScript (Promises & Fetch)",
            descriptionFa = "کنترل وظایف غیرهمگام با setTimeout، setInterval، ساخت وعده‌ها (Promise) و دریافت اطلاعات با async/await.",
            codeSnippet = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <title>Async/Await در JS</title>
    <style>
        body { font-family: Tahoma; padding: 20px; }
        button { background: #2563eb; color: white; border: none; padding: 8px 16px; border-radius: 6px; cursor: pointer; }
    </style>
</head>
<body>
    <h3>شبیه‌سازی دریافت داده از سرور آکادمی:</h3>
    <button onclick="fetchStudentData()">دریافت اطلاعات دوره</button>
    <p id="status"></p>

    <script>
        function simulateApiCall() {
            return new Promise((resolve) => {
                setTimeout(() => {
                    resolve({ course: "وب دولوپر کامل", sessions: 40, status: "تکمیل موفقیت‌آمیز" });
                }, 1500);
            });
        }

        async function fetchStudentData() {
            const statusEl = document.getElementById("status");
            statusEl.innerText = "در حال برقراری ارتباط با سرور تیروتیر...";
            
            const data = await simulateApiCall();
            statusEl.innerText = "داده دریافت شد: دوره " + data.course + " (" + data.sessions + " جلسه) - " + data.status;
        }
    </script>
</body>
</html>""",
            exerciseFa = "یک بلاک try/catch برای مدیریت خطاهای احتمالی پیرامون await قرار دهید.",
            solutionFa = "try { const data = await simulateApiCall(); } catch(err) { console.error(err); }",
            tags = listOf("JavaScript", "Async", "Await", "Promise", "Timeout")
        ),
        Lesson(
            id = "m3_hcj65",
            moduleId = 3,
            prefix = "hcj65",
            titleFa = "چیت‌شیت کامل جاوااسکریپت (دایره‌المعارف سریع)",
            titleEn = "JavaScript Cheat Sheet",
            descriptionFa = "مرجع تمامی دستورات کلیدی جاوااسکریپت از Hello World تا توابع Arrow، مدیریت ارور و Async.",
            codeSnippet = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <title>چیت‌شیت جاوااسکریپت - تیروتیر</title>
    <style>
        body { font-family: Tahoma; padding: 20px; background: #f8fafc; }
        .box { background: white; padding: 12px; margin: 8px 0; border-radius: 6px; border-right: 4px solid #f59e0b; }
        code { color: #b45309; font-weight: bold; }
    </style>
</head>
<body>
    <h1>خلاصه گرامر و کلیدواژه‌های JavaScript</h1>
    <div class="box"><b>تعریف توابع فلشی:</b> <code>const add = (a, b) => a + b;</code></div>
    <div class="box"><b>تجزیه اشیا (Destructuring):</b> <code>const { name, age } = user;</code></div>
    <div class="box"><b>ذخیره‌سازی لوکال:</b> <code>localStorage.setItem('key', val);</code></div>
    <div class="box"><b>مدیریت خطا:</b> <code>try { ... } catch (e) { ... }</code></div>
</body>
</html>""",
            exerciseFa = "یک نمونه از تابع map را به سبک Arrow Function بنویسید.",
            solutionFa = "const squares = [1, 2, 3].map(x => x * x);",
            tags = listOf("JavaScript", "Cheat Sheet", "ES6", "Arrow Functions")
        ),

        // MODULE 4: DOM & WEB APIS
        Lesson(
            id = "m4_hcj06",
            moduleId = 4,
            prefix = "hcj06",
            titleFa = "دستکاری المان‌های DOM و تغییر محتوا با کلیک",
            titleEn = "DOM Manipulation & Event Listeners",
            descriptionFa = "پیدا کردن المان با getElementById و تغییر دادن innerText یا innerHTML در زمان رخداد کلیک.",
            codeSnippet = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <title>تعامل با DOM</title>
    <style>
        body { font-family: Tahoma; padding: 30px; text-align: center; }
        #message { font-size: 20px; color: #475569; margin-bottom: 20px; }
        button { background: #059669; color: white; border: none; padding: 10px 20px; border-radius: 6px; font-size: 16px; cursor: pointer; }
    </style>
</head>
<body>
    <p id="message">متن اولیه قبل از کلیک</p>
    <button onclick="updateText()">کلیک برای به‌روزرسانی</button>

    <script>
        function updateText() {
            const el = document.getElementById("message");
            el.innerText = "متن با موفقیت توسط جاوااسکریپت تغییر یافت! 🎉";
            el.style.color = "#2563eb";
            el.style.fontWeight = "bold";
        }
    </script>
</body>
</html>""",
            exerciseFa = "رنگ پس‌زمینه دکمه را در همان تابع تغییر دهید.",
            solutionFa = "event.target.style.background = '#dc2626';",
            tags = listOf("DOM", "Events", "Click", "getElementById")
        ),
        Lesson(
            id = "m4_hcj52",
            moduleId = 4,
            prefix = "hcj52",
            titleFa = "ایجاد داینامیک گره‌های DOM (Create & Append Element)",
            titleEn = "Dynamic Node Creation",
            descriptionFa = "ساخت عناصر جدید در لحظه با createElement و الحاق آن‌ها با appendChild به صفحه.",
            codeSnippet = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <title>ایجاد المان‌های پویا</title>
    <style>
        body { font-family: Tahoma; padding: 20px; }
        li { background: #f1f5f9; margin: 5px 0; padding: 8px 12px; border-radius: 4px; }
        button { background: #2563eb; color: white; border: none; padding: 8px 16px; border-radius: 4px; cursor: pointer; }
    </style>
</head>
<body>
    <h3>لیست مهارت‌های ثبت شده:</h3>
    <ul id="skillsList">
        <li>HTML5 & CSS3</li>
    </ul>
    <input type="text" id="newSkill" placeholder="عنوان مهارت جدید...">
    <button onclick="addSkill()">افزودن مهارت</button>

    <script>
        function addSkill() {
            const input = document.getElementById("newSkill");
            if (!input.value.trim()) return;

            const li = document.createElement("li");
            li.innerText = input.value;
            document.getElementById("skillsList").appendChild(li);
            input.value = "";
        }
    </script>
</body>
</html>""",
            exerciseFa = "یک دکمه 'حذف' درون هر تگ li اضافه کنید تا کاربر بتواند آن را پاک کند.",
            solutionFa = "const btn = document.createElement('button'); btn.onclick = () => li.remove(); li.appendChild(btn);",
            tags = listOf("DOM", "createElement", "appendChild", "Dynamic")
        ),
        Lesson(
            id = "m4_hcj61",
            moduleId = 4,
            prefix = "hcj61",
            titleFa = "وب‌سرویس نقشه تعاملی با iFrame",
            titleEn = "Embedded Google Maps Web API",
            descriptionFa = "تعبیه نقشه‌های تعاملی جغرافیایی با فریم‌های امن وب برای نمایش موقعیت آموزشگاه.",
            codeSnippet = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <title>نقشه موقعیت آموزشگاه تیروتیر</title>
    <style>
        body { font-family: Tahoma; padding: 20px; text-align: center; }
        .map-card { max-width: 600px; margin: auto; border-radius: 12px; overflow: hidden; box-shadow: 0 4px 10px rgba(0,0,0,0.15); }
    </style>
</head>
<body>
    <h2>موقعیت موسسه اندیشه پردازان آنزان (تیروتیر)</h2>
    <p>دسترسی سریع و آسان به شعبات و کارگاه‌های تخصصی</p>
    <div class="map-card">
        <iframe 
            src="https://maps.google.com/maps?q=Tehran&t=&z=13&ie=UTF8&iwloc=&output=embed" 
            width="100%" height="320" style="border:0;" allowfullscreen="" loading="lazy">
        </iframe>
    </div>
</body>
</html>""",
            exerciseFa = "عرض و ارتفاع آی‌فریم را به اندازه دلخواه تغییر دهید.",
            solutionFa = "width='100%' height='400'",
            tags = listOf("Web API", "iframe", "Maps", "Embed")
        ),

        // MODULE 5: JQUERY
        Lesson(
            id = "m5_hcj66",
            moduleId = 5,
            prefix = "hcj66",
            titleFa = "مبانی کتابخانه jQuery و رویداد آماده‌سازی سند",
            titleEn = "jQuery Basics & $(document).ready()",
            descriptionFa = "نحوه کار با سلکتورهای جی‌کوئری، متدهای .text()، .html() و کنترل نمایش .hide() و .show().",
            codeSnippet = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <title>مبانی jQuery</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        body { font-family: Tahoma; padding: 25px; text-align: center; }
        #targetBox { padding: 20px; background: #e0e7ff; color: #1e3a8a; border-radius: 8px; margin: 15px auto; max-width: 400px; font-weight: bold; }
        button { background: #4338ca; color: white; border: none; padding: 8px 14px; border-radius: 6px; margin: 4px; cursor: pointer; }
    </style>
</head>
<body>
    <h2>تست متدهای پایه جی‌کوئری:</h2>
    <div id="targetBox">سلام از جی‌کوئری در آکادمی تیروتیر!</div>
    <button id="btnChange">تغییر متن</button>
    <button id="btnHide">مخفی کردن</button>
    <button id="btnShow">نمایش مجدد</button>

    <script>
        $(document).ready(function() {
            $("#btnChange").click(function() {
                $("#targetBox").text("متن با متد .text() تغییر کرد!");
            });
            $("#btnHide").click(function() {
                $("#targetBox").hide(300);
            });
            $("#btnShow").click(function() {
                $("#targetBox").show(300);
            });
        });
    </script>
</body>
</html>""",
            exerciseFa = "با استفاده از متد .css() رنگ پس‌زمینه باکس را به زرد تغییر دهید.",
            solutionFa = "$('#targetBox').css('background-color', '#fef08a');",
            tags = listOf("jQuery", "Selectors", "Hide", "Show")
        ),
        Lesson(
            id = "m5_hcj67",
            moduleId = 5,
            prefix = "hcj67",
            titleFa = "افکت‌ها و انیمیشن‌های لغزشی و محوشدگی jQuery",
            titleEn = "jQuery Effects & Animations",
            descriptionFa = "کاربرد fadeIn، fadeOut، slideUp، slideDown و toggle در ساخت منوها و آکاردئون‌ها.",
            codeSnippet = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <title>افکت‌های جی‌کوئری</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        body { font-family: Tahoma; padding: 20px; text-align: center; }
        #banner { width: 250px; height: 120px; background: #059669; color: white; margin: 15px auto; border-radius: 10px; display: flex; align-items: center; justify-content: center; font-weight: bold; }
        button { background: #065f46; color: white; border: none; padding: 8px 12px; margin: 4px; border-radius: 4px; cursor: pointer; }
    </style>
</head>
<body>
    <h3>کنترل افکت‌های دیداری با جی‌کوئری:</h3>
    <div id="banner">باکس متحرک تیروتیر</div>
    <br>
    <button id="fadeToggle">Fade Toggle</button>
    <button id="slideToggle">Slide Toggle</button>

    <script>
        $(document).ready(function() {
            $("#fadeToggle").click(function() {
                $("#banner").fadeToggle(400);
            });
            $("#slideToggle").click(function() {
                $("#banner").slideToggle(400);
            });
        });
    </script>
</body>
</html>""",
            exerciseFa = "با متد .animate() اندازه باکس را تغییر دهید.",
            solutionFa = "$('#banner').animate({ width: '300px' }, 500);",
            tags = listOf("jQuery", "Fade", "Slide", "Toggle", "Animation")
        ),

        // MODULE 6: BEST PRACTICES & SEO
        Lesson(
            id = "m6_hcj78",
            moduleId = 6,
            prefix = "hcj78",
            titleFa = "اصول سئو داخلی (On-Page SEO) برای فرانت‌اند کاران",
            titleEn = "Common SEO Suggestions for Web Developers",
            descriptionFa = "بهینه‌سازی تگ‌های عنوان، توضیحات متا، صفات alt، ساختار سلسله‌مراتبی هدینگ‌ها و اسکیما.",
            codeSnippet = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- عنوان دقیق زیر ۶۰ کاراکتر حاوی کلمات کلیدی -->
    <title>آموزشگاه هوش مصنوعی و فرانت‌اند تیروتیر | مدرک بین‌المللی</title>
    <!-- توضیحات متای استاندارد ۱۵۰ تا ۱۶۰ کاراکتر -->
    <meta name="description" content="یادگیری گام به گام برنامه‌نویسی وب فرانت‌اند و هوش مصنوعی در آموزشگاه فنی‌حرفه‌ای تیروتیر با مدرک معتبر.">
    <!-- متاتگ‌های شبکه اجتماعی Open Graph -->
    <meta property="og:title" content="آکادمی تیروتیر - دوره جامع وب">
    <meta property="og:type" content="website">
</head>
<body style="font-family: Tahoma; padding: 20px; line-height: 1.8;">
    <h1>راهنمای جامع سئو برای توسعه‌دهندگان وب</h1>
    <h2>۱. انتخاب عنوان مناسب (Title Tag)</h2>
    <p>عنوان مهم‌ترین عامل جذب کاربر و ایندکس موتور جستجو است.</p>
    <h2>۲. درج صفات جایگزین تصویر (Alt Tags)</h2>
    <p>برای بهبود دسترسی‌پذیری و سئو تصاویر حتماً از توضیحات فارسی و دقیق استفاده کنید.</p>
</body>
</html>""",
            exerciseFa = "یک متاتگ canonical برای جلوگیری از صفحات تکراری اضافه کنید.",
            solutionFa = "<link rel='canonical' href='https://tirotir.ir/web-course'>",
            tags = listOf("SEO", "Meta Tags", "Open Graph", "Alt Text")
        ),
        Lesson(
            id = "m6_hcj79",
            moduleId = 6,
            prefix = "hcj79",
            titleFa = "۲۰ رهنمود حرفه‌ای توسعه فرانت‌اند و کارایی (Performance)",
            titleEn = "20 Professional Front-End Development Guidelines",
            descriptionFa = "تکنیک‌های حیاتی شامل بارگذاری تنبل (Lazy loading)، پیش‌اتصال (preconnect)، ویژگی‌های ARIA و تصویر واکنش‌گرا با picture.",
            codeSnippet = """<!DOCTYPE html>
<html lang="fa" dir="rtl">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>بهترین تجارب فرانت‌اند - تیروتیر</title>
    <!-- ۱. پیش‌اتصال به سرورهای فونت و منابع خارجی -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <style>
        /* ۲. استفاده از متغیرهای CSS (Custom Properties) */
        :root {
            --primary: #2563eb;
            --bg-canvas: #f8fafc;
        }
        body { font-family: Tahoma; background: var(--bg-canvas); padding: 20px; }
        .rule { background: white; padding: 12px; margin: 8px 0; border-radius: 6px; border-right: 4px solid var(--primary); }
    </style>
</head>
<body>
    <h1>۲۰ رهنمود کلیدی توسعه فرانت‌اند</h1>
    <div class="rule">۱. بارگذاری تنبل تصاویر با <code>loading="lazy"</code></div>
    <div class="rule">۲. استفاده از نقش‌های ARIA برای نابینایان و دستیارهای صوتی</div>
    <div class="rule">۳. بارگذاری غیرهمگام اسکریپت‌ها با ویژگی <code>defer</code></div>
    <div class="rule">۴. استفاده از تگ <code>&lt;picture&gt;</code> با اندازه‌های مختلف تصویر</div>
</body>
</html>""",
            exerciseFa = "یک ساختار picture با فرمت‌های webp و jpg برای تصاویر ریسپانسیو بنویسید.",
            solutionFa = "<picture><source srcset='img.webp' type='image/webp'><img src='img.jpg' alt='تیروتیر'></picture>",
            tags = listOf("Best Practices", "Performance", "ARIA", "Lazy Loading")
        )
    )

    fun getLessonsForModule(moduleId: Int): List<Lesson> {
        return lessons.filter { it.moduleId == moduleId }
    }

    fun getLessonById(id: String): Lesson? {
        return lessons.find { it.id == id }
    }

    fun getLessonByPrefix(prefix: String): Lesson? {
        return lessons.find { it.prefix.equals(prefix, ignoreCase = true) }
    }
}
