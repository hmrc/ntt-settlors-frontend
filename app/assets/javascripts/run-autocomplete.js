var selectEl = document.querySelector('#country-autocomplete')

accessibleAutocomplete.enhanceSelectElement({
    autoselect: true,
    minLength: 2,
    selectElement: selectEl
})