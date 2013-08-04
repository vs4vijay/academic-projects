<?php
/**
 * Save the submitted file to disk
 */
if (is_uploaded_file($_FILES['img']['tmp_name'])) {
    move_uploaded_file($_FILES['img']['tmp_name'], "img.jpg");
}
?>