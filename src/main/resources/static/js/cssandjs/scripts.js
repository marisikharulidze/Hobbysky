function openEditProfile() {
    document.getElementById('edit-profile-modal').style.display = 'block';
    document.getElementById('name').value = document.getElementById('artist-name').innerText;
    document.getElementById('location').value = document.getElementById('artist-location').innerText;
    document.getElementById('bio').value = document.getElementById('artist-bio').innerText;
}

function closeEditProfile() {
    document.getElementById('edit-profile-modal').style.display = 'none';
}

function validateForm() {
    let name = document.getElementById('name').value;
    let location = document.getElementById('location').value;
    let bio = document.getElementById('bio').value;
    
    if (name === "" || location === "" || bio === "") {
        alert("All fields must be filled out");
        return false;
    }

    document.getElementById('artist-name').innerText = name;
    document.getElementById('artist-location').innerText = location;
    document.getElementById('artist-bio').innerText = bio;

    closeEditProfile();
    return false; // Prevent form submission
}

function triggerAvatarUpload() {
    document.getElementById('avatar-upload').click();
}

function updateAvatar() {
    const input = document.getElementById('avatar-upload');
    if (input.files && input.files[0]) {
        const reader = new FileReader();
        reader.onload = function (e) {
            document.getElementById('profile-avatar').src = e.target.result;
        };
        reader.readAsDataURL(input.files[0]);
    }
}
