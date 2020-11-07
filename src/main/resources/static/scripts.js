$(document).ready(function () {
  $("#artist > tbody").append(
    '<tr style="background-color: #0074B7" class="royal_blue"><td colspan="3"><span data-toggle="modal" data-target="#add-artist" style="color: white" title="Create artist" class="fa fa-plus fa-3x" aria-hidden="true"> </span></td></tr>'
  );

  function getId(obj, prefix) {
    return $(obj)
      .attr("class")
      .match(prefix + "-[0-9]+")[0]
      .split("-")[1]
      .trim();
  }

  $(".remove-album").on("click", function (evt) {
    var result = confirm("Sure you want to delete?");
    if (!result) {
      return;
    }
    let albumId = getId(this, "album");
    let artistId = getId(this, "artist");
    $.ajax({
      url: `artist/${artistId}/albums/${albumId}`,
      type: "DELETE",
      success: function (data) {
        alert("successfully deleted the album from artist");
        window.location.reload();
      },
    });
  });

  $(".delete_artist").on("click", function (evt) {
    var result = confirm("Sure you want to delete?");
    if (!result) {
      return;
    }
    let artistId = getId(this, "artist");
    $.ajax({
      url: `artist/${artistId}`,
      type: "DELETE",
      success: function (data) {
        alert("successfully deleted the artist");
        window.location.reload();
      },
    });
  });

  $("form.add-artist").on("submit", function (evt) {
    evt.preventDefault();
    let name = $("#artist-name").val().trim();

    if (name == null || name.length == 0 || name.length < 3) {
      alert("name cannot be null/empty/length less than 3");
      return;
    }
    $.ajax({
      url: `artist/${name}`,
      type: "POST",
      success: function (data) {
        alert("successfully created the artist");
        window.location.reload();
      },
      complete: function () {},
    });
  });
  $("span[data-id]").on("click", function (evt) {
    $("#artist-id").val($(this).attr("data-id"));
  });

  $("form.add-album").on("submit", function (evt) {
    evt.preventDefault();
    let name = $("#album-name").val().trim();
    if (name == null || name.length == 0 || name.length < 3) {
      alert("name cannot be null/empty/length less than 3");
      return;
    }
    let url = $("#cover-url").val().trim();
    let match = url.match("http.*(jpg|jpeg|gif|png)");
    if (match == null || match < 0) {
      alert("enter valid image url");
      return;
    }
    let artistId = $("#artist-id").val().split("-")[1].trim();
    $.ajax({
      url: `artist/${artistId}/add`,
      dataType: "json",
      contentType: "application/json",
      type: "POST",
      data: JSON.stringify({
        name: name,
        cover_url: url,
      }),
      success: function (data) {
        alert("successfully added album to artist");
        window.location.reload();
      },
      complete: function () {
        $("#artist-id").val("");
      },
    });
  });
});
