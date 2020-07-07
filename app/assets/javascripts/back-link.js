// Back link mimics browser back functionality, adapted from trusts-frontend
var docReferrer = document.referrer

if (window.history && window.history.replaceState && typeof window.history.replaceState === 'function') {
window.history.replaceState(null, null, window.location.href);
}

var list= document.getElementsByClassName("govuk-back-link");
for (var i = 0; i < list.length; i++) {
    list[i].addEventListener("click", function(e) {
        e.preventDefault();
        window.history.back();
    }, false);
}