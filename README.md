# IRPOBA-Group-3
Themengebiet 5 - CI with Actions (Intro)

[![CI Pipeline](https://github.com/EdisSuljevic/IRPOBA-Group-3/actions/workflows/ci.yml/badge.svg)](https://github.com/EdisSuljevic/IRPOBA-Group-3/actions/workflows/ci.yml)

## 🛠 CI/CD Monitoring Features
In diesem Projekt nutzen wir GitHub Actions für eine automatisierte Pipeline:
* **Multi-Platform Build:** Tests laufen auf `ubuntu-latest` und `windows-latest`.
* **Caching:** Java-Abhängigkeiten werden gecacht, um die Build-Zeit zu optimieren.
* **Performance Tracking:** Die Build-Dauer und Artefakt-Grösse werden bei jedem Run gemessen und in der **Job Summary** visualisiert.
* **Artifacts:** Kompilierte `.class`-Dateien werden automatisch als Build-Artefakte gespeichert.
