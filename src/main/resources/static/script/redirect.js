$('a').removeClass('active');
$('a:contains(Statistiques)').addClass('active');
$("#main-content").load("page/statistiques.html");

function show(page) {
//admin
    if (page == 'listaffecterbug') {
        $('a').removeClass('active');
        $('a:contains(Ferme)').addClass('active');
        $("#main-content").load("page/listaffecterbug.html");

        event.preventDefault();
    }

    if (page == "affecterticket") {
        $('a').removeClass('active');
        $('a:contains(Parcelle)').addClass('active');
        $("#main-content").load("page/affecterticket.html");
        event.preventDefault();
    }
//client
    if (page == "tickets") {
        $('a').removeClass('active');
        $('a:contains(Plante)').addClass('active');
        $("#main-content").load("page/tickets.html");
        event.preventDefault();
    }
//dev
    if (page == "ticketAttrib") {
        $('a').removeClass('active');
        $('a:contains(TypeSole)').addClass('active');
        $("#main-content").load("page/ticketAttrib.html");
        event.preventDefault();
    }
    if (page == "updatestatus") {
        $('a').removeClass('active');
        $('a:contains(Marques)').addClass('active');
        $("#main-content").load("page/updatestatus.html");
        event.preventDefault();
    }
//profile
    if (page == "profile") {
        $('a').removeClass('active');
        $('a:contains(Marques)').addClass('active');
        $("#main-content").load("page/profile.html");
        event.preventDefault();
    }

}