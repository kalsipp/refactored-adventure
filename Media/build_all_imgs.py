
from img_conv_stolen import convert_image
import os
import shutil

def conv_imgs(output_dir):
    print(output_dir)
    # list_of_image_endings = ["jpg", "png"]
    # all_dirs = os.listdir(".")
    # all_imgs = [file for file in all_dirs if any([file.endswith(ending) for ending in list_of_image_endings])]

    # for filename in all_imgs:
    #     convert_image(filename)

    # all_converted_img_names = [file.split(".")[0]+".img" for file in all_imgs]
    # for filename in all_converted_img_names:
    #     print(output_dir)
        # shutil.move("./" + filename, output_dir)

if __name__ == '__main__':
    conv_imgs(sys.argv[1])