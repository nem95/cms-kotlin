<#include "header.ftl">
<div class="container">
    <div class="row">
        <div class="col-sm-6 offset-sm-3">
            <div class="card">
                <div class="card-body">
                    <form action="" method="post">
                        <div class="form-group">
                            <label for="title">Title</label>
                            <input type="text" class="form-control" id="title" name="title" placeholder="Enter a title">
                        </div>
                        <div class="form-group">
                            <label for="textContent">Content</label>
                            <textarea class="form-control" id="textContent" rows="3" name="textContent" placeholder="Write your article here"></textarea>
                        </div>

                        <button type="submit" class="btn btn-info">Publish your article</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "footer.ftl">
