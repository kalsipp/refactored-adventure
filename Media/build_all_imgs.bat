@echo off
echo Building images for target
py -3 build_all_imgs.py ../out/artifacts/CrawlEclipse_jar/sprites/
echo Building images for unit tests
py -3 build_all_imgs.py ../sprites/