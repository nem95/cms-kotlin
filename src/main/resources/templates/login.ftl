<#include "header.ftl">
<div class="container">
    <div class="row">
        <div class="col-sm-6 offset-sm-3">
            <div class="card">
                <div class="card-body">
                    <form action="/login" method="post">
                        <div class="form-group">
                            <label for="username">Username</label>
                            <input type="text" class="form-control" id="username" name="username" placeholder="Enter username">
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" class="form-control" id="password" name="password" placeholder="Password">
                        </div>

                        <button type="submit" class="btn btn-info">Login</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<#include "footer.ftl">
