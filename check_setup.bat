@echo off
echo ========================================
echo  KIEM TRA JDK VA CAU HINH PROJECT
echo ========================================
echo.

echo [1/4] Kiem tra JDK da cai dat...
java -version 2>nul
if %errorlevel% neq 0 (
    echo ❌ JDK CHUA DUOC CAI DAT!
    echo.
    echo 📥 HUONG DAN CAI DAT JDK 17:
    echo 1. Vao trang: https://adoptium.net/temurin/releases/
    echo 2. Chon: Windows x64, JDK, Version 17 (LTS)
    echo 3. Download va cai dat
    echo 4. Chay lai script nay
    echo.
    pause
    exit /b 1
) else (
    echo ✅ JDK da duoc cai dat
)

echo.
echo [2/4] Kiem tra Maven...
mvn -version 2>nul
if %errorlevel% neq 0 (
    echo ⚠️  Maven khong co trong PATH (co the van chay duoc trong IDE)
) else (
    echo ✅ Maven da duoc cai dat
)

echo.
echo [3/4] Kiem tra MySQL...
mysql --version 2>nul
if %errorlevel% neq 0 (
    echo ⚠️  MySQL khong co trong PATH (can cai dat va chay MySQL)
    echo 📥 Tai MySQL: https://dev.mysql.com/downloads/mysql/
) else (
    echo ✅ MySQL da duoc cai dat
)

echo.
echo [4/4] Kiem tra cac file can thiet...
if exist "database\create_database.sql" (
    echo ✅ File database\create_database.sql ton tai
) else (
    echo ❌ Thieu file database\create_database.sql
)

if exist "database\create_table.sql" (
    echo ✅ File database\create_table.sql ton tai
) else (
    echo ❌ Thieu file database\create_table.sql
)

if exist "database\insert_data.sql" (
    echo ✅ File database\insert_data.sql ton tai
) else (
    echo ❌ Thieu file database\insert_data.sql
)

if exist "src\main\resources\META-INF\persistence.xml" (
    echo ✅ File persistence.xml ton tai
) else (
    echo ❌ Thieu file persistence.xml
)

echo.
echo ========================================
echo  HUONG DAN CHAY PROJECT
echo ========================================
echo.
echo BƯỚC 1: Cấu hình IntelliJ IDEA
echo ──────────────────────────────
echo 1. File → Project Structure (Ctrl+Alt+Shift+S)
echo 2. Project SDK: Chọn JDK 17
echo 3. Language level: 17
echo 4. OK
echo.
echo BƯỚC 2: Reload Maven Dependencies
echo ────────────────────────────────
echo 1. View → Tool Windows → Maven
echo 2. Click Reload All Maven Projects (🔄)
echo.
echo BƯỚC 3: Rebuild Project
echo ──────────────────────
echo 1. Build → Rebuild Project (Ctrl+F9)
echo.
echo BƯỚC 4: Chạy Database Scripts
echo ─────────────────────────────
echo 1. Mở MySQL Workbench hoặc Command Line
echo 2. Chạy: SOURCE database/create_database.sql
echo 3. Chạy: SOURCE database/create_table.sql
echo 4. Chạy: SOURCE database/insert_data.sql
echo.
echo BƯỚC 5: Test Database Connection
echo ───────────────────────────────
echo 1. Right-click TestDatabase.java
echo 2. Run 'TestDatabase.main()'
echo 3. Nếu thấy "DATABASE CONNECTION SUCCESSFUL" → OK
echo.
echo BƯỚC 6: Chạy Ứng Dụng Chính
echo ────────────────────────────
echo 1. Right-click AppLauncher.java
echo 2. Run 'AppLauncher.main()'
echo 3. Giao diện Java Swing sẽ xuất hiện
echo.
echo ========================================
echo  THONG TIN HO TRO
echo ========================================
echo.
echo 📧 Neu gap loi, kiem tra:
echo 1. JDK 17 da cai dat dung chua
echo 2. IntelliJ IDEA da cau hinh JDK 17 chua
echo 3. MySQL dang chay va co du lieu mau
echo 4. persistence.xml co dung password MySQL khong
echo.
echo 🎯 Loi thuong gap:
echo - "Cannot resolve symbol": Chua cau hinh JDK trong IDE
echo - "Unknown database": Chua chay database scripts
echo - "Access denied": Sai password trong persistence.xml
echo.
pause
