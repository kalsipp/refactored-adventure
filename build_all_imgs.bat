cd Media

set images[0] = "bestimg2.png"
set images[1] = "grid.png"
set images[2] = "stonewall_simple_6464.png"

for %%x in images do (
   python img_conv_stolen.py  %%x
)


rem python img_conv_stolen.py bestimg2.png
rem python img_conv_stolen.py grid.png
rem cpython img_conv_stolen.py stonewall_simple_6464.png

cd ..
