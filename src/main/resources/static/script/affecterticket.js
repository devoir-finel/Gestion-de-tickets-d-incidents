$(document)
    .ready(
        function() {

            table = $('#ttickets')
                .DataTable({
                    ajax: {
                        url: "admin/ticketsAtt",
                        dataSrc: ''
                    },
                    columns: [{
                        data: "id"
                    },
                        {
                            data: "description"
                        },
                        {
                            data: "nom"
                        },
                        {
                            data: "priorite"
                        },
                        {
                            data: "bug.description"
                        },
                        {
                            data: "user.username"
                        },
                        {
                            data: "dev.username"
                        },
                        {
                            "render": function() {
                                return '<button type="button" class="btn btn-outline-danger supprimer">Supprimer</button>';
                            }
                        },
                        {
                            "render": function() {
                                return '<button type="button" class="btn btn-outline-secondary desafercetr">Desaffecter</button>';
                            }
                        }
                    ]

                });
            //list des tickets non atribuer
            $.ajax({
                url: '/admin/ticketsNonAtt',
                type: 'GET',
                success: function(data) {
                    var option = '';
                    data.forEach(e => {
                        option += '<option value =' + e.id + '>' + e.nom + '</option>';
                    });

                    $('#tickets').append(option);
                },
                error: function(jqXHR, textStatus,
                                errorThrown) {
                    console.log(textStatus);
                }

            });
            //list des devs
            $.ajax({
                url: '/users/alldevs',
                type: 'GET',
                success: function(data) {
                    var option = '';
                    data.forEach(e => {
                        option += '<option value =' + e.userId + '>' + e.username + '</option>';
                    });

                    $('#devs').append(option);
                },
                error: function(jqXHR, textStatus,
                                errorThrown) {
                    console.log(textStatus);
                }
            });
            //button ajouter ticket
            $('#btn').click(
                function() {

                    var bug = $("#tickets");
                    var user = $("#devs");


                    if ($('#btn').text() == 'Affecter') {
                        var m = {

                            nom: null,
                            description: null,
                            priorite: null,
                            bug: {
                                id: bug.val(),
                            },
                            user: {
                                userId: user.val(),
                            }
                        };
                        $.ajax({
                            url: 'admin/affecterbug',
                            contentType: "application/json",
                            dataType: "json",
                            data: JSON.stringify(m),
                            type: 'POST',
                            async: false,
                            success: function(data, textStatus,
                                              jqXHR) {
                                table.ajax.reload();
                            },
                            error: function(jqXHR, textStatus,
                                            errorThrown) {
                                console.log(textStatus);
                            }
                        });
                        $("#main-content").load(
                            "./page/affecterticket.html");
                    }
                });
            $('#table-content')
                .on(
                    'click',
                    '.supprimer',
                    function() {

                        var id = $(this).closest('tr').find(
                            'td').eq(0).text();
                        var oldLing = $(this).closest('tr')
                            .clone();
                        var newLigne = '<tr style="position: relative;" class="bg-light" ><th scope="row">' +
                            id +
                            '</th><td colspan="10" style="height: 100%;">';
                        newLigne += '<h4 class="d-inline-flex">Voulez vous vraiment supprimer ce produit ? </h4>';
                        newLigne += '<button type="button" class="btn btn-outline-primary btn-sm confirmer" style="margin-left: 25px;">Oui</button>';
                        newLigne += '<button type="button" class="btn btn-outline-danger btn-sm annuler" style="margin-left: 25px;">Non</button></td></tr>';

                        $(this).closest('tr').replaceWith(
                            newLigne);
                        $('.annuler').click(
                            function() {
                                $(this).closest('tr')
                                    .replaceWith(
                                        oldLing);
                            });
                        $('.confirmer')
                            .click(
                                function(e) {
                                    e.preventDefault();
                                    $
                                        .ajax({
                                            url: 'admin/deleteticket/' +
                                                id,
                                            data: {},
                                            type: 'DELETE',
                                            async: false,
                                            success: function(
                                                data,
                                                textStatus,
                                                jqXHR) {
                                                if (data
                                                    .includes("error") == true) {
                                                    $(
                                                        "#error")
                                                        .modal();
                                                } else {
                                                    table.ajax
                                                        .reload();
                                                }
                                            },
                                            error: function(
                                                jqXHR,
                                                textStatus,
                                                errorThrown) {
                                                $(
                                                    "#error")
                                                    .modal();
                                            }
                                        });

                                });

                    });
           // desafercetr
            $('#table-content')
                .on(
                    'click',
                    '.desafercetr',
                    function() {

                        var id = $(this).closest('tr').find(
                            'td').eq(0).text();
                        var oldLing = $(this).closest('tr')
                            .clone();
                        var newLigne = '<tr style="position: relative;" class="bg-light" ><th scope="row">' +
                            id +
                            '</th><td colspan="10" style="height: 100%;">';
                        newLigne += '<h4 class="d-inline-flex">Voulez vous vraiment desafercetr ce ticket ? </h4>';
                        newLigne += '<button type="button" class="btn btn-outline-primary btn-sm confirmer" style="margin-left: 25px;">Oui</button>';
                        newLigne += '<button type="button" class="btn btn-outline-danger btn-sm annuler" style="margin-left: 25px;">Non</button></td></tr>';

                        $(this).closest('tr').replaceWith(
                            newLigne);
                        $('.annuler').click(
                            function() {
                                $(this).closest('tr')
                                    .replaceWith(
                                        oldLing);
                            });
                        $('.confirmer')
                            .click(
                                function(e) {
                                    e.preventDefault();
                                    $
                                        .ajax({
                                            url: 'admin/descafecter/' +
                                                id,
                                            data: {},
                                            type: 'GET',
                                            async: false,
                                            success: function(
                                                data,
                                                textStatus,
                                                jqXHR) {
                                                if (data
                                                    .includes("error") == true) {
                                                    $(
                                                        "#error")
                                                        .modal();
                                                } else {
                                                    table.ajax
                                                        .reload();
                                                }
                                            },
                                            error: function(
                                                jqXHR,
                                                textStatus,
                                                errorThrown) {
                                                $(
                                                    "#error")
                                                    .modal();
                                            }
                                        });

                                });

                    });


            $('#table-content').on(
                'click',
                '.modifier',
                function() {
                    var btn = $('#btn');
                    var id = $(this).closest('tr').find('td').eq(0)
                        .text();;
                    var code = $(this).closest('tr').find('td').eq(
                        1).text();
                    var libelle = $(this).closest('tr').find('td')
                        .eq(2).text();

                    btn.text('Modifier');
                    $("#code").val(code);
                    $("#libelle").val(libelle);
                    $("#id").val(id);

                    btn.click(function(e) {
                        e.preventDefault();
                        var m = {
                            id: $("#id").val(),
                            code: $("#code").val(),
                            libelle: $("#libelle").val()
                        };
                        if ($('#btn').text() == 'Modifier') {
                            $.ajax({
                                url: 'parcelle/save',
                                contentType: "application/json",
                                dataType: "json",
                                data: JSON.stringify(m),
                                type: 'POST',
                                async: false,
                                success: function(data,
                                                  textStatus, jqXHR) {
                                    table.ajax.reload();

                                    btn.text('Ajouter');
                                },
                                error: function(jqXHR, textStatus,
                                                errorThrown) {
                                    console.log(textStatus);
                                }
                            });
                            $("#main-content").load(
                                "./page/marque.html");
                        }
                    });
                });

            // function remplir(data) {
            // var contenu = $('#table-content');
            // var ligne = "";
            // for (i = 0; i < data.length; i++) {
            // ligne += '<tr><th scope="row">' + data[i].id + '</th>';
            // ligne += '<td>' + data[i].code + '</td>';
            // ligne += '<td>' + data[i].nom + '</td>';
            // ligne += '<td>' + data[i].prix + '</td>';
            // ligne += '<td>' + data[i].dateAchat + '</td>';
            // ligne += '<td><button type="button" class="btn
            // btn-outline-danger
            // supprimer">Supprimer</button></td>';
            // ligne += '<td><button type="button" class="btn
            // btn-outline-secondary
            // modifier">Modifier</button></td></tr>';
            // }
            // contenu.html(ligne);
            // }

            // $.ajax({
            // url: 'produits/all',
            // data: {op: ''},
            // type: 'GET',
            // async: false,
            // success: function (data, textStatus, jqXHR) {
            // console.log(data);
            // remplir(data);
            // },
            // error: function (jqXHR, textStatus, errorThrown) {
            // console.log(textStatus);
            // }
            // });
        });