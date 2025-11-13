function togglePassword(passFieldId, passIconId) {
    const input = document.getElementById(passFieldId);
    const icon = document.getElementById(passIconId);
    const isHidden = input.type === "password";

    input.type = isHidden ? "text" : "password";
    
    icon.classList.toggle("fa-eye-slash")
    icon.classList.toggle("fa-eye")
}