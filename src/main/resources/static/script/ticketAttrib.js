$(document)
    .ready(
        function () {
            $.ajax({
                url: "/userid",
                type: "GET",
                success: function (data) {
                    table = $('#tmytickets')
                        .DataTable({
                            ajax: {
                                url: "dev/listticket/" + data,
                                dataSrc: ''
                            },
                            columns: [{
                                data: "id"
                            },
                                {
                                    data: "statut"
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
                                }


                            ]

                        });
                }
            });
            //list des tickets
            $.ajax({
                url: "/userid",
                type: "GET",
                success: function (data) {
                    $.ajax({
                        url: "dev/listticket/" + data,
                        type: 'GET',
                        success: function (data) {
                            var option = '';
                            data.forEach(e => {
                                option += '<option value =' + e.id + '>' + e.nom + '</option>';
                            });

                            $('#tickets').append(option);
                        },
                        error: function (jqXHR, textStatus,
                                         errorThrown) {
                            console.log(textStatus);
                        }

                    });
                }
            });

            //button ajouter ticket
            $('#btn').click(
                function () {
                    var bug = $("#tickets");
                    var statut = $("#statut");
                    if ($('#btn').text() == 'Update') {
                        var m = {
                            nom: null,
                            description: null,
                            priorite: null,
                            bug: {
                                id: bug.val(),
                            },
                            user: null,
                            statut:statut.val()
                        };
                        $.ajax({
                            url: 'dev/affecterbug',
                            contentType: "application/json",
                            dataType: "json",
                            data: JSON.stringify(m),
                            type: 'POST',
                            async: false,
                            success: function (data, textStatus,
                                               jqXHR) {
                                table.ajax.reload();
                            },
                            error: function (jqXHR, textStatus,
                                             errorThrown) {
                                console.log(textStatus);
                            }
                        });
                        $("#main-content").load(
                            "./page/ticketAttrib.html");
                    }
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