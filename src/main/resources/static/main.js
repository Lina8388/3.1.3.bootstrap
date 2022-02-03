/*
$('document').ready(function() {



    $('.table #myModal').on('click',function(event){

        event.preventDefault();

        var href= $(this).attr('href');

        $.get(href, function(user, status){
            $('#id').val(user.id);
            $('#name').val(user.name);
            $('#surname').val(user.surname);
            $('#age').val(user.age);
            $('#email').val(user.email);
            $('#password').val(user.password);
            $('#role').val(user.role);


        });

        $('#myModal').modal();

    });

    $('.table #deleteButton').on('click',function(event) {
        event.preventDefault();


        var href = $(this).attr('href');
        $('#deleteModal #delRef').attr('href', href);

        $('#deleteModal').modal();

    });

});

*/
