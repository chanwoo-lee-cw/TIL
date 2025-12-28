import os

target_dir = "./"

files = sorted(os.listdir(target_dir))

count = 1
for filename in files:
    old_path = os.path.join(target_dir, filename)

    if not os.path.isfile(old_path):
        continue

    name, ext = os.path.splitext(filename)
    new_name = f"{count:03d}{ext}"
    new_path = os.path.join(target_dir, new_name)

    os.rename(old_path, new_path)
    count += 1