package com.example.data.model

data class QuizQuestion(
    val id: String,
    val questionFa: String,
    val optionsFa: List<String>,
    val correctIndex: Int,
    val explanationFa: String
)

data class CodeBugPuzzle(
    val id: String,
    val titleFa: String,
    val brokenCode: String,
    val bugDescriptionFa: String,
    val optionsFa: List<String>,
    val correctOptionIndex: Int,
    val fixedCode: String,
    val explanationFa: String
)

object QuizData {

    val milestoneQuizzes: Map<String, List<QuizQuestion>> = mapOf(
        "html_css" to listOf(
            QuizQuestion(
                id = "q1",
                questionFa = "کدام تگ در HTML5 برای قرار دادن ناوبری و منوی اصلی سایت به صورت معنایی توصیه می‌شود؟",
                optionsFa = listOf("<navigation>", "<nav>", "<menu-bar>", "<header-nav>"),
                correctIndex = 1,
                explanationFa = "تگ <nav> تگ معنایی رسمی HTML5 برای المان‌های ناوبری اصلی صفحه است."
            ),
            QuizQuestion(
                id = "q2",
                questionFa = "در مدل جعبه‌ای CSS (Box Model) ترتیب لایه‌ها از داخل به خارج چگونه است؟",
                optionsFa = listOf(
                    "Content -> Margin -> Border -> Padding",
                    "Content -> Padding -> Border -> Margin",
                    "Margin -> Border -> Padding -> Content",
                    "Content -> Border -> Padding -> Margin"
                ),
                correctIndex = 1,
                explanationFa = "ابتدا Content (محتوا)، سپس Padding (فاصله درونی)، بعد Border (حاشیه) و نهایتاً Margin (فاصله بیرونی) قرار دارد."
            ),
            QuizQuestion(
                id = "q3",
                questionFa = "کدام مقدار برای justify-content در Flexbox المان‌ها را با فواصل مساوی و چسبیده به کناره‌ها می‌چیند؟",
                optionsFa = listOf("center", "space-around", "space-between", "space-evenly"),
                correctIndex = 2,
                explanationFa = "مقدار space-between اولین آیتم را در ابتدا، آخرین را در انتها و مابقی را با فواصل برابر پخش می‌کند."
            ),
            QuizQuestion(
                id = "q4",
                questionFa = "در CSS Grid دستور repeat(3, 1fr) به چه معناست؟",
                optionsFa = listOf(
                    "ایجاد ۳ ستون با عرض یکسان و منعطف",
                    "تکرار ۳ ردیف با عرض ثابت",
                    "۳ برابر کردن اندازه فونت المان‌ها",
                    "تعریف ۳ ستون با حداقل ۱۰۰ پیکسل"
                ),
                correctIndex = 0,
                explanationFa = "عبارت repeat(3, 1fr) یعنی ۳ ستون بساز که هر کدام ۱ بخش کسری (Fractional Unit) از فضا را اشغال کنند."
            )
        ),
        "js_dom" to listOf(
            QuizQuestion(
                id = "q5",
                questionFa = "خروجی عبارت [1, 2, 3].map(x => x * 2) در جاوااسکریپت چیست؟",
                optionsFa = listOf("[1, 2, 3]", "[2, 4, 6]", "12", "undefined"),
                correctIndex = 1,
                explanationFa = "متد .map روی تک‌تک اعضا تابع را اجرا کرده و آرایه جدیدی با مقادیر دوبرابر شده برمی‌گرداند."
            ),
            QuizQuestion(
                id = "q6",
                questionFa = "کدام مقدار در جاوااسکریپت در شرایط شرطی Falsy محسوب نمی‌شود؟",
                optionsFa = listOf("0", "\"\"", "null", "\"0\""),
                correctIndex = 3,
                explanationFa = "رشته غیرخالی حتی اگر حاوی کاراکتر '0' باشد Truthy است."
            ),
            QuizQuestion(
                id = "q7",
                questionFa = "تفاوت اصلی بین let و const چیست؟",
                optionsFa = listOf(
                    "متغیر const قابل مقداردهی مجدد نیست ولی let تغییرپذیر است",
                    "متغیر let فقط عدد می‌پذیرد ولی const همه‌چیز",
                    "متغیر const در حافظه ذخیره نمی‌شود",
                    "تتفاوتی ندارند"
                ),
                correctIndex = 0,
                explanationFa = "متغیرهای تعریف شده با const ثابت بوده و نمی‌توان آنها را مجدداً با = مقداردهی کرد."
            ),
            QuizQuestion(
                id = "q8",
                questionFa = "کدام متد برای افزودن شنونده رویداد (Event Listener) به المان DOM استفاده می‌شود؟",
                optionsFa = listOf(".attach()", ".addEventListener()", ".onEvent()", ".listen()"),
                correctIndex = 1,
                explanationFa = "متد استاندارد W3C برای اتصال رویدادها addEventListener(event, callback) است."
            )
        )
    )

    val codeBugPuzzles: List<CodeBugPuzzle> = listOf(
        CodeBugPuzzle(
            id = "bug1",
            titleFa = "خطای اسکریپت دکمه و عدم دسترسی به DOM",
            brokenCode = """<script>
    document.getElementById("myBtn").addEventListener("click", () => {
        alert("سلام!");
    });
</script>
<button id="myBtn">کلیک کنید</button>""",
            bugDescriptionFa = "چرا با کلیک روی دکمه هیچ پیامی نمایش داده نمی‌شود و در کنسول خطای Cannot read properties of null رخ می‌دهد؟",
            optionsFa = listOf(
                "کد اسکریپت قبل از رندر شدن دکمه در DOM اجرا شده و المان هنوز وجود ندارد",
                "شناسه myBtn در جاوااسکریپت رزرو شده است",
                "تابع alert در مرورگرهای جدید منسوخ شده",
                "باید به جای addEventListener از onClick مستقیم استفاده می‌شد"
            ),
            correctOptionIndex = 0,
            fixedCode = """<button id="myBtn">کلیک کنید</button>
<script>
    document.getElementById("myBtn").addEventListener("click", () => {
        alert("سلام!");
    });
</script>""",
            explanationFa = "مرورگرها اسکریپت را خط به خط اجرا می‌کنند. اگر اسکریپت در بالای المان باشد، هنوز المان دکمه ساخته نشده است. راه حل: قرار دادن اسکریپت در انتهای body یا استفاده از defer یا DOMContentLoaded."
        ),
        CodeBugPuzzle(
            id = "bug2",
            titleFa = "عدم مرکز شدن المان در Flexbox",
            brokenCode = """.container {
    display: block;
    justify-content: center;
    align-items: center;
}""",
            bugDescriptionFa = "چرا دستورات justify-content و align-items روی جعبه تاثیری نمی‌گذارند؟",
            optionsFa = listOf(
                "باید display: flex یا inline-flex باشد تا خواص Flexbox فعال شوند",
                "نیاز به نصب افزونه فلکس‌باکس در مرورگر است",
                "دستور justify-content فقط در Grid کاربرد دارد",
                "باید رنگ پس‌زمینه تعریف شود"
            ),
            correctOptionIndex = 0,
            fixedCode = """.container {
    display: flex;
    justify-content: center;
    align-items: center;
}""",
            explanationFa = "خواص justify-content و align-items مختص کانتینرهای flex و grid هستند و روی display: block کارایی ندارند."
        )
    )
}
